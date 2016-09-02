package br.ic.ufal.logic.scanner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import br.ic.ufal.logic.token.BiconditionalToken;
import br.ic.ufal.logic.token.CloseParenthesisToken;
import br.ic.ufal.logic.token.ConditionalToken;
import br.ic.ufal.logic.token.ConjunctionToken;
import br.ic.ufal.logic.token.ConstantToken;
import br.ic.ufal.logic.token.EndToken;
import br.ic.ufal.logic.token.ExclusiveDisjunctionToken;
import br.ic.ufal.logic.token.InclusiveDisjunctionToken;
import br.ic.ufal.logic.token.NegationToken;
import br.ic.ufal.logic.token.OpenParenthesisToken;
import br.ic.ufal.logic.token.PropositionToken;
import br.ic.ufal.logic.token.SpaceToken;
import br.ic.ufal.logic.token.StartToken;
import br.ic.ufal.logic.token.Token;

/**
 * 
 * @author Anderson Santos
 * 
 */
public class Scanner {
	private static HashMap<String, String> symbolTable = new HashMap<String, String>();

	/* Static initializer for HashMap of symbols */
	static {
		symbolTable.put("biconditional1", "<=>");
		symbolTable.put("biconditional2", "<->");
		symbolTable.put("close_parenthesis", ")");
		symbolTable.put("conditional1", "=>");
		symbolTable.put("conditional2", "->");
		symbolTable.put("conjunction1", "&");
		symbolTable.put("conjunction2", "^");
		symbolTable.put("end", "$");
		symbolTable.put("exclusive_disjunction", "+");
		symbolTable.put("inclusive_disjunction", "v");
		symbolTable.put("negation1", "~");
		symbolTable.put("negation2", "!");
		symbolTable.put("open_parenthesis", "(");
		symbolTable.put("space", " ");
		symbolTable.put("start", "@");
		symbolTable.put("constant_true", "1");
		symbolTable.put("constant_false", "0");
	}

	private LinkedList<Token> tokenStream;
	private int i, positionOfFirstBadChar;
	private final String statement, inclusive_disjunction = symbolTable.get("inclusive_disjunction"),
			open_parenthesis = symbolTable.get("open_parenthesis"),
			close_parenthesis = symbolTable.get("close_parenthesis"),
			negation1 = symbolTable.get("negation1"),
			negation2 = symbolTable.get("negation2"),
			conjunction1 = symbolTable.get("conjunction1"),
			conjunction2 = symbolTable.get("conjunction2"),
			exclusive_disjunction = symbolTable.get("exclusive_disjunction"),
			conditional1 = symbolTable.get("conditional1"),
			conditional2 = symbolTable.get("conditional2"),
			biconditional1 = symbolTable.get("biconditional1"),
			biconditional2 = symbolTable.get("biconditional2"),
			space = symbolTable.get("space"),
			constant_true = symbolTable.get("constant_true"),
			constant_false = symbolTable.get("constant_false");

	public Scanner(final String statement) {
		this.statement = statement;
		tokenStream = new LinkedList<Token>();
	}

	/**
	 * @return <i>LinkedList</i> tokenStream
	 */
	public LinkedList<Token> getTokenStream() {
		return tokenStream;
	}

	/**
	 * @throws ScannerException
	 */
	public void tokenize() throws ScannerException {
		final int statementLength = statement.length();
		i = 0;
		positionOfFirstBadChar = -1;
		char c;

		// Indicates the beginning of the statement
		tokenStream.add(new StartToken(symbolTable.get("start")));

		while (i < statementLength) {
			c = statement.charAt(i);

			/*
			 * Checks for errors at the inserted statement, add and creates
			 * token objects
			 */
			if (c == inclusive_disjunction.charAt(0)) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
				tokenStream.add(new InclusiveDisjunctionToken(inclusive_disjunction, i));
			} else if (c == open_parenthesis.charAt(0)) {
                positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
                tokenStream.add(new OpenParenthesisToken(open_parenthesis, i));
			} else if (c == close_parenthesis.charAt(0)) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
				tokenStream.add(new CloseParenthesisToken(close_parenthesis, i));
			} else if ((c == negation1.charAt(0)) || (c == negation2.charAt(0))) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
				tokenStream.add(new NegationToken(String.valueOf(c), i));
			} else if ((c == conjunction1.charAt(0)) || (c == conjunction2.charAt(0))) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
				tokenStream.add(new ConjunctionToken(String.valueOf(c), i));
			} else if (c == exclusive_disjunction.charAt(0)) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
				tokenStream.add(new ExclusiveDisjunctionToken(exclusive_disjunction, i));
			} else if (c == constant_false.charAt(0)) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
				tokenStream.add(new ConstantToken(constant_false, i, false));
			} else if (c == constant_true.charAt(0)) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
				tokenStream.add(new ConstantToken(constant_true, i, true));
			} else if (c == conditional1.charAt(0)) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
				scanMultiCharSymbol(conditional1, new ConditionalToken(conditional1, i), true);
			} else if (c == conditional2.charAt(0)) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
				scanMultiCharSymbol(conditional2, new ConditionalToken(conditional2, i), true);
			} else if (c == biconditional1.charAt(0)) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
				final int current = i;
				final boolean isOK = scanMultiCharSymbol(biconditional1, new BiconditionalToken(biconditional1, i), false);
				if (!isOK) {
					i = current;
					scanMultiCharSymbol(biconditional2, new BiconditionalToken(biconditional2, i), true);
				}
			} else if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
				tokenStream.add(new PropositionToken(String.valueOf(Character.toUpperCase(c)), i));
			} else if (c == space.charAt(0)) {
				positionOfFirstBadChar = reportError(positionOfFirstBadChar, i - 1);
			} else if (positionOfFirstBadChar == -1) {
				positionOfFirstBadChar = i;
			}
			i++;
		}
		positionOfFirstBadChar = reportError(positionOfFirstBadChar, --i);
		// Indicates the end of the statement
		tokenStream.add(new EndToken(symbolTable.get("end")));
		// System.out.println(getTokenStream());
	}

	/**
	 * 
	 */
	public void reformat() {
		final LinkedList<Token> list = tokenStream;
		final ListIterator<Token> iterator = list.listIterator(0);
		final LinkedList<Token> reformattedtokenStream = new LinkedList<Token>();
		Token token1 = null, token2 = null;
		int position = -1;
		if (iterator.hasNext()) {
			token1 = iterator.next();
		}
		while (iterator.hasNext()) {
			token2 = iterator.next();
			final int token1Type = token1.getType(), token2Type = token2.getType();
			token1.setPosition(position);
			reformattedtokenStream.add(token1);
			position = position + token1.getSymbol().length();
			if (token1Type == Token.START) {
				if (token2Type == Token.BINARY_OPERATOR) {
					final SpaceToken spaceToken = new SpaceToken(symbolTable.get("space"), position);
					reformattedtokenStream.add(spaceToken);
					position = position + spaceToken.getSymbol().length();
				}
			} else if (token1Type == Token.BINARY_OPERATOR) {
				final SpaceToken spaceToken = new SpaceToken(symbolTable.get("space"), position);
				reformattedtokenStream.add(spaceToken);
				position = position + spaceToken.getSymbol().length();
			} else if (token1Type == Token.UNARY_OPERATOR) {
				if (token2Type == Token.BINARY_OPERATOR) {
					final SpaceToken spaceToken = new SpaceToken(symbolTable.get("space"), position);
					reformattedtokenStream.add(spaceToken);
					position = position + spaceToken.getSymbol().length();
				}
			} else if ((token1Type == Token.PROPOSITION) || (token1Type == Token.CONSTANT) || (token1Type == Token.CLOSE_PARENTHESIS)) {
				if ((token2Type != Token.CLOSE_PARENTHESIS) && (token2Type != Token.END)) {
					final SpaceToken spaceToken = new SpaceToken(symbolTable.get("space"), position);
					reformattedtokenStream.add(spaceToken);
					position = position + spaceToken.getSymbol().length();
				}
			} else if (token1Type == Token.OPEN_PARENTHESIS) {
				if (token2Type == Token.CLOSE_PARENTHESIS) {
					final SpaceToken spaceToken = new SpaceToken(symbolTable.get("space"), position);
					reformattedtokenStream.add(spaceToken);
					position = position + spaceToken.getSymbol().length();
				}
			}
			token1 = token2;
		}
		if (token2 != null) {
			token2.setPosition(position);
			reformattedtokenStream.add(token2);
		}
		tokenStream = reformattedtokenStream;
	}

	/**
	 * Prints the string of the reformatted statement.
	 * 
	 * @return
	 */
	public String getStatement() {
		final StringBuilder builder = new StringBuilder();
		final LinkedList<Token> list = tokenStream;
		final ListIterator<Token> iterator = list.listIterator(0);
		while (iterator.hasNext()) {
			final Token token = iterator.next();
			final int tokenType = token.getType();
			if ((tokenType != Token.START) && (tokenType != Token.END)) {
				builder.append(token.getSymbol());
			}
		}
		return builder.toString();
	}

	/**
	 * Used when symbols have more than just one char (e.g. "<=>", "<->", "=>",
	 * "->").
	 * 
	 * @param symbol
	 * @param token
	 * @param isFinalMatch
	 * @return boolean value
	 * @throws ScannerException
	 */
	private boolean scanMultiCharSymbol(final String symbol, final Token token,
			final boolean isFinalMatch) throws ScannerException {
		positionOfFirstBadChar = -1;
		int symbolPos = 1;
		final int symbolLength = symbol.length(), initialValueOfI = i;
		while (true) {
			i++;
			if (i >= statement.length()) {
				if (isFinalMatch) {
					positionOfFirstBadChar = reportError(initialValueOfI, i - 1);
				} else {
					return false;
				}
			}
			final char c = statement.charAt(i);
			if ((symbolPos < symbolLength) && (c == symbol.charAt(symbolPos))) {
				if (symbolPos + 1 == symbolLength) {
					if (positionOfFirstBadChar != -1) {
						if (isFinalMatch) {
							positionOfFirstBadChar = reportError(
									initialValueOfI, i);
						} else {
							return false;
						}
					}
					tokenStream.add(token);
					break;
				}
				symbolPos++;
			} else {
				if (positionOfFirstBadChar == -1) {
					positionOfFirstBadChar = i;
				}
				if ((c == negation1.charAt(0)) || (c == negation2.charAt(0))
						|| (c == constant_true.charAt(0))
						|| (c == constant_false.charAt(0))
						|| (c == space.charAt(0))
						|| (c == open_parenthesis.charAt(0))
						|| ((c >= 'A') && (c <= 'Z'))
						|| ((c >= 'a') && (c <= 'z'))) {
					i--;
					if (isFinalMatch) {
						positionOfFirstBadChar = reportError(initialValueOfI, i);
					} else {
						return false;
					}
				} else {
					symbolPos++;
				}
			}
		}
		return true;
	}

	/**
	 * @param positionOfFirstBadChar
	 * @param positionOfCurrentChar
	 * @return -1 if there is any error
	 * @throws ScannerException
	 */
	private int reportError(final int positionOfFirstBadChar,
			final int positionOfCurrentChar) throws ScannerException {
		if (positionOfFirstBadChar != -1) {
			if (positionOfCurrentChar - positionOfFirstBadChar < 1) {
				throw new ScannerException(ScannerException.ILLEGAL_SYMBOL,
						positionOfFirstBadChar + 1);
			} else {
				throw new ScannerException(ScannerException.ILLEGAL_SYMBOLS,
						positionOfFirstBadChar + 1, positionOfCurrentChar + 1);
			}
		}
		return -1;
	}
}
