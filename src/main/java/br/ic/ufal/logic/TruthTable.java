package br.ic.ufal.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

import br.ic.ufal.logic.evaluator.BinaryEvaluator;
import br.ic.ufal.logic.evaluator.UnaryEvaluator;
import br.ic.ufal.logic.token.ConstantToken;
import br.ic.ufal.logic.token.Token;
import br.ic.ufal.logic.token.ValueToken;

/**
 * 
 * @author Anderson Santos
 * 
 */
public class TruthTable {
	public static int UNDEFINED = -1, TAUTOLOGY = 0, IDENTITY = 1,
			CONDITIONAL = 2, CONTRADICTION = 3;
	public static String[] EVALUATION_DEFINITION = { "Tautologia",
			"Identidade", "Condicional", "Contradição" };
	private int numberOfPropositions, numberOfOperators;
	private final int displayMethod, numberOfLines;
	private final boolean alphabetizePropositions;
	private boolean arePositionsCalculated;
	private final LinkedList<Token> postfixStream;
	private final Vector<String> propositionNamesVector;
	private final HashMap<String, Integer> propositionNamesToPositionsMap;
	private final String infixStatement;
	private final int[] operatorPositions;

	/**
	 * 
	 * @param infixStatement
	 * @param postfixStream
	 * @param displayMethod
	 * @param alphabetizePropositions
	 */
	public TruthTable(final String infixStatement,
			final LinkedList<Token> postfixStream, final int displayMethod,
			final boolean alphabetizePropositions) {
		this.infixStatement = infixStatement;
		this.postfixStream = postfixStream;
		this.displayMethod = displayMethod;
		this.alphabetizePropositions = alphabetizePropositions;
		this.arePositionsCalculated = false;
		propositionNamesVector = new Vector<String>();
		propositionNamesToPositionsMap = new HashMap<String, Integer>();
		extractPropositionData();
		operatorPositions = new int[numberOfOperators];
		numberOfLines = (int) Math.pow(2, numberOfPropositions);
	}

	/**
	 * 
	 * @return infixStatement
	 */
	public String getStatement() {
		return infixStatement;
	}

	/**
	 * 
	 * @return numberOfLines
	 */
	public int getNumberOfLines() {
		return numberOfLines;
	}

	/**
	 * 
	 * @return numberOfColumns
	 */
	public int getNumberOfColumns() {
		getPositionOfMainColumn();
		int numberOfColumns = operatorPositions.length;
		if (numberOfColumns == 0) {
			numberOfColumns = 1;
		}
		return numberOfColumns;
	}

	/**
	 * 
	 * @return numberOfPropositions
	 */
	public int getNumberOfPropositions() {
		return numberOfPropositions;
	}

	/**
	 * 
	 * @return
	 */
	public String[] getPropositionNames() {
		return propositionNamesVector.toArray(new String[propositionNamesVector
				.size()]);
	}

	/**
	 * 
	 * @return
	 */
	public int getDisplayMethod() {
		return displayMethod;
	}

	/**
	 * @return
	 */
	public String getHeaderSeparator() {
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numberOfPropositions; i++) {
			builder.append("-");
			final int propositionLength = propositionNamesVector.elementAt(i)
					.length();
			for (int j = 0; j < propositionLength; j++) {
				builder.append("-");
			}
			builder.append("-+");
		}
		final int infixLengthWithPadding = infixStatement.length() + 2;
		for (int i = 0; i < infixLengthWithPadding; i++) {
			builder.append("-");
		}
		return builder.toString();
	}

	/**
	 * @param isForTextVersion
	 * @return
	 */
	public String getHeader(final boolean isForTextVersion) {
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numberOfPropositions; i++) {
			builder.append(" ");
			builder.append(propositionNamesVector.elementAt(i));
			builder.append(" ");
			if (isForTextVersion) {
				builder.append("|");
			}
		}
		builder.append(" ");
		builder.append(infixStatement);
		builder.append(" ");
		return builder.toString();
	}

	/**
	 * @return
	 */
	public int getPositionOfMainColumn() {
		final boolean[] binaryPropositionValue = getBinaryFormat(0);
		final LinkedList<Token> substitutedPostfix = substituteTruthValues(binaryPropositionValue);
		evaluatePostfix(substitutedPostfix, binaryPropositionValue);

		if (operatorPositions.length != 0) {
			return operatorPositions[operatorPositions.length - 1];
		} else {
			return 0;
		}
	}

	/**
	 * @return
	 */
	public int getEvaluation() {
		int pos, result, i = 0;
		final int upperBound = numberOfLines / 2, oneLess = numberOfLines - 1;
		char ch;
		boolean done = false;
		final String row = computeRow(0);

		if (operatorPositions.length != 0) {
			pos = operatorPositions[operatorPositions.length - 1];
		} else {
			pos = 0;
		}
		ch = row.charAt(pos);
		if (ch == 'V') {
			result = TAUTOLOGY;
		} else if (ch == '1') {
			result = IDENTITY;
		} else {
			result = CONTRADICTION;
		}

		while (i < upperBound) {
			if ((computeRow(i).charAt(pos) == ch)
					&& (computeRow(oneLess - i).charAt(pos) == ch)) {
				i += 1;
			} else {
				done = true;
				break;
			}
		}

		if (!done) {
			return result;
		} else {
			return CONDITIONAL;
		}
	}

	/**
	 * @param index
	 * @return
	 */
	public String computeRow(final int index) {
		final boolean[] binaryPropositionValues = getBinaryFormat(index);
		final LinkedList<Token> substitutedPostfix = substituteTruthValues(binaryPropositionValues);
		return evaluatePostfix(substitutedPostfix, binaryPropositionValues);
	}

	/**
	 * @return
	 */
	public int getColumnInfoHeight() {
		computeRow(0);
		int height = 2;
		if ((operatorPositions != null) && (operatorPositions.length > 0))
			height += (int) Math.log10(operatorPositions.length);
		return height;
	}

	/**
	 * @param currentColumn
	 * @return
	 */
	public String[] getColumnOrderStrings(int currentColumn) {
		if (currentColumn > operatorPositions.length - 1)
			currentColumn = operatorPositions.length - 1;
		final String[] columnOrderStrings = new String[getColumnInfoHeight()];
		if (operatorPositions.length == 0) {
			columnOrderStrings[0] = "^ ";
			columnOrderStrings[1] = "1 ";
		} else {
			final char[][] charArray = new char[columnOrderStrings.length][infixStatement
					.length() + 1];
			for (int i = charArray.length - 1; i >= 0; i--) {
				for (int j = charArray[i].length - 1; j >= 0; j--) {
					charArray[i][j] = ' ';
				}
			}
			for (int i = currentColumn; i >= 0; i--) {
				final String valueString = String.valueOf(i + 1);
				final int valueLength = valueString.length();
				charArray[0][operatorPositions[i]] = '^';
				for (int j = valueLength - 1; j >= 0; j--) {
					charArray[columnOrderStrings.length - valueLength + j][operatorPositions[i]] = valueString
							.charAt(j);
				}
			}
			for (int i = charArray.length - 1; i >= 0; i--) {
				columnOrderStrings[i] = new String(charArray[i]);
			}
		}
		return columnOrderStrings;
	}

	/**
	 * 
	 */
	private void extractPropositionData() {
		final ListIterator<Token> iterator = postfixStream.listIterator(0);
		while (iterator.hasNext()) {
			final Token token = iterator.next();
			final int tokenType = token.getType();
			if (tokenType == Token.PROPOSITION) {
				final String tokenSymbol = token.getSymbol();
				if (!propositionNamesToPositionsMap.containsKey(tokenSymbol)) {
					// This method needs to be revisited if we allow
					// propositions
					// to be greater than 1 character in length.
					propositionNamesVector.add(tokenSymbol);
					propositionNamesToPositionsMap.put(tokenSymbol,
							new Integer(0));
					numberOfPropositions++;
				}
			} else if ((tokenType == Token.UNARY_OPERATOR)
					|| (tokenType == Token.BINARY_OPERATOR)) {
				numberOfOperators++;
			}
		}
		if (alphabetizePropositions) {
			Collections.sort(propositionNamesVector);
		}
		String tokenSymbol;
		for (int i = 0; i < numberOfPropositions; i++) {
			tokenSymbol = propositionNamesVector.elementAt(i);
			propositionNamesToPositionsMap.put(tokenSymbol, new Integer(i));
		}
	}

	/**
	 * @param n
	 * @return
	 */
	public boolean[] getBinaryFormat(int n) {
		int base = numberOfLines >> 1;
		final boolean[] binary = new boolean[numberOfPropositions];
		for (int i = 0; i < numberOfPropositions; i++) {
			final int temp = n - base;
			if (temp >= 0) {
				n = temp;
				binary[i] = true;
			}
			base >>= 1;
		}
		return binary;
	}

	/**
	 * @param binary
	 * @return
	 */
	private LinkedList<Token> substituteTruthValues(final boolean[] binary) {
		final LinkedList<Token> substitutedPostfix = new LinkedList<Token>();
		final ListIterator<Token> iterator = postfixStream.listIterator(0);
		while (iterator.hasNext()) {
			final Token token = iterator.next();
			final int tokenType = token.getType();
			if (tokenType == Token.PROPOSITION) {
				final int i = propositionNamesToPositionsMap.get(
						token.getSymbol()).intValue();
				substitutedPostfix.add(new ValueToken(binary[i], displayMethod,
						token.getPosition()));
			} else if (tokenType == Token.CONSTANT) {
				final ConstantToken constantToken = (ConstantToken) token;
				substitutedPostfix.add(new ValueToken(constantToken.getValue(),
						displayMethod, token.getPosition()));
			} else {
				substitutedPostfix.add(token);
			}
		}
		return substitutedPostfix;
	}

	/**
	 * @param postfixStream
	 * @param binary
	 * @return
	 */
	private String evaluatePostfix(final LinkedList<Token> postfixStream,
			final boolean[] binary) {
		final char[] charArray = new char[infixStatement.length()];
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = ' ';
		}
		final Stack<Token> stack = new Stack<Token>();
		final ListIterator<Token> iterator = postfixStream.listIterator(0);
		int pos = 0;

		while (iterator.hasNext()) {
			final Token token = iterator.next();
			final int tokenType = token.getType();
			if (tokenType == Token.BINARY_OPERATOR) {
				final ValueToken stackPopped2 = (ValueToken) stack.pop(), stackPopped1 = (ValueToken) stack
						.pop();
				final BinaryEvaluator binaryEvaluator = (BinaryEvaluator) token;
				//Evaluating token (Binary)
				final ValueToken valueToken = binaryEvaluator.evaluate(stackPopped1, stackPopped2);
				if (iterator.hasNext()) {
					stack.push(valueToken);
				}
				charArray[valueToken.getPosition()] = valueToken.getSymbol()
						.charAt(0);

				if (!arePositionsCalculated) {
					operatorPositions[pos++] = token.getPosition()
							+ token.getOffset();
				}
			} else if (tokenType == Token.UNARY_OPERATOR) {
				final ValueToken stackPopped = (ValueToken) stack.pop();
				final UnaryEvaluator unaryEvaluator = (UnaryEvaluator) token;
				//Evaluating token (Unary)
				final ValueToken valueToken = unaryEvaluator.evaluate(stackPopped);
				if (iterator.hasNext()) {
					stack.push(valueToken);
				}
				charArray[valueToken.getPosition()] = valueToken.getSymbol()
						.charAt(0);
				if (!arePositionsCalculated) {
					operatorPositions[pos++] = token.getPosition()
							+ token.getOffset();
				}
			} else {
				stack.push(token);
			}
		}
		if (!stack.empty()) {
			final ValueToken stackPopped = (ValueToken) stack.pop();
			charArray[stackPopped.getPosition()] = TruthValue
					.getTruthValueString(stackPopped.getValue(), displayMethod)
					.charAt(0);
		}
		arePositionsCalculated = true;
		return new String(charArray);
	}

	/**
	 * 
	 * @return
	 */
	public List<String> listSolution() {
		List<String> solution = new ArrayList<String>();
		for (int i = this.getNumberOfLines() - 1; i >= 0; i--) {
			StringTokenizer st = new StringTokenizer(this.computeRow(i));
			while (st.hasMoreTokens()) {
				solution.add(st.nextToken());
			}

		}
		return solution;
	}
}
