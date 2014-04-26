package br.ic.ufal.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import br.ic.ufal.logic.parser.Parser;
import br.ic.ufal.logic.parser.ParserException;
import br.ic.ufal.logic.scanner.Scanner;
import br.ic.ufal.logic.scanner.ScannerException;
import br.ic.ufal.logic.token.Token;

/**
 * 
 * @author Anderson Santos
 * 
 */
public class TruthTableConstructor {

	private static TruthTableConstructor instance;
	private String state;
	private int stateInt;
	private String truthTableAdaptor;

	private static List<String> resolution;

	private TruthTableConstructor() {
	}

	public static TruthTableConstructor getInstance() {

		resolution = new ArrayList<String>();

		if (instance == null)
			instance = new TruthTableConstructor();

		return instance;
	}

	/**
	 * Generates the truth table and show the evaluation of the given statement.
	 * 
	 * @param expression
	 */
	public List<String> generateWithAnswers(String expression) {
		generate(expression, true);

		return resolution;
	}

	/**
	 * 
	 * @param expression
	 */
	public List<String> generateWithNoAnswers(String expression) {
		generate(expression, false);

		return resolution;
	}

	/**
	 * 
	 * @param expression
	 * @param withAnswers
	 *            <i>?</i>
	 */
	private void generate(String expression, boolean withAnswers) {

		// final long startTime = System.currentTimeMillis();
		// final long endTime;
		// final double RATIO = 1000.0;
		final int numberOfArguments = expression.length();

		if (resolution.size() > 0)
			resolution.removeAll(resolution);

		if (numberOfArguments == 0) {
			System.out.println("Usage: type the statment");
			// return null;
		}

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < numberOfArguments; i++) {
			builder.append(expression.charAt(i));
		}

		final String statement = builder.toString();

		System.out.print("Input: " + statement + "\n");
		// resolution.add("Input: " + statement);

		final Scanner scanner = new Scanner(statement);

		try {
			scanner.tokenize();
		} catch (final ScannerException se) {
			System.err.println("Error: " + se.getMessage());
			// System.exit(1);
		}

		scanner.reformat();

		if (scanner.getStatement().length() == 0) {
			System.err
					.println("Error: Cannot construct table, no statement entered.");
			// System.exit(1);
		}

		System.out.print("Reformatted: " + scanner.getStatement());
		// resolution.add("Reformatted: " + scanner.getStatement());

		final Parser parser = new Parser(scanner.getTokenStream());

		LinkedList<Token> postfixStream = null;

		try {
			parser.parse();
			parser.removeUnnecessaryParentheses();
			postfixStream = parser.getPostfixStream();
			final ListIterator<Token> iterator = postfixStream.listIterator(0);
			System.out.print("\nPostfix: ");
			while (iterator.hasNext()) {
				final Token token = iterator.next();
				System.out.print(token.getSymbol());
			}
			System.out.print("\n\n");
		} catch (final ParserException pe) {
			System.err.println("Error: " + pe.getMessage());
			// System.exit(1);
		}

		final TruthTable truthTable = new TruthTable(parser.getStatement(),
				postfixStream, TruthValue.TRUE_FALSE, true);

		builder = new StringBuilder();
		builder.append(truthTable.getHeader(true));

		resolution.add(truthTable.getHeader(true));

		builder.append("\n");
		builder.append(truthTable.getHeaderSeparator());

		resolution.add(truthTable.getHeaderSeparator());

		builder.append("\n");

		 System.out.print(builder.toString());

		// resolution.add(builder.toString());

		final int numberOfLines = truthTable.getNumberOfLines();

		for (int i = numberOfLines - 1; i >= 0; --i) {
			builder = new StringBuilder();
			final boolean[] binaryPropositionValues = truthTable
					.getBinaryFormat(i);
			final int binaryLength = binaryPropositionValues.length;
			for (int j = 0; j < binaryLength; j++) {
				// builder.append(" ");
				builder.append(" ");
				builder.append(TruthValue.getTruthValueString(
						binaryPropositionValues[j], TruthValue.TRUE_FALSE));
				builder.append(" |");
			}
			builder.append(" ");

			// ----------------------------------------------------

			if (withAnswers) {
				builder.append(truthTable.computeRow(i));
			}

			// ----------------------------------------------------

			builder.append(" ");

			 System.out.println(builder.toString());

			resolution.add(builder.toString());

			if (i != 0) {
				builder.append("\n");
			}

		}
		
		// endTime = System.currentTimeMillis();

		state = TruthTable.EVALUATION_DEFINITION[truthTable.getEvaluation()];
		stateInt = truthTable.getEvaluation();

		/*
		 * if (truthTable.getNumberOfLines() == 1) {
		 * 
		 * // ---------------------------------------------------- if
		 * (withAnswers) {
		 * 
		 * System.out.println("\n\n" + truthTable.getNumberOfLines() + " row / "
		 * + (endTime - startTime) / RATIO + " s");
		 * 
		 * resolution.add(truthTable.getNumberOfLines() + " row / " + (endTime -
		 * startTime) / RATIO + " s"); }
		 * 
		 * else { System.out.println("\n\n" + truthTable.getNumberOfLines() +
		 * " row");
		 * 
		 * resolution.add(truthTable.getNumberOfLines() + " row"); } //
		 * ----------------------------------------------------
		 * 
		 * } else { // ---------------------------------------------------- if
		 * (withAnswers) { System.out.println("\n\n" +
		 * truthTable.getNumberOfLines() + " rows / " + (endTime - startTime) /
		 * RATIO + " s"); resolution.add(truthTable.getNumberOfLines() +
		 * " rows / " + (endTime - startTime) / RATIO + " s"); }
		 * 
		 * else { System.out.println("\n\n" + truthTable.getNumberOfLines() +
		 * " rows"); resolution.add(truthTable.getNumberOfLines() + " rows"); }
		 * // ---------------------------------------------------- }
		 */

		// ----------------------------------------------------
		if (withAnswers) {
			System.out.print("Evaluation: ");
			System.out.println(TruthTable.EVALUATION_DEFINITION[truthTable
					.getEvaluation()]);
			
		}
		// ----------------------------------------------------
	}

	/**
	 * 
	 * @param expression
	 * @return
	 */
	public String getMainTruthValuesAnswers(String expression) {
				final int numberOfArguments = expression.length();

				if (resolution.size() > 0)
					resolution.removeAll(resolution);

				if (numberOfArguments == 0) {
					System.out.println("Usage: type the statment");
				}

				StringBuilder builder = new StringBuilder();

				for (int i = 0; i < numberOfArguments; i++) {
					builder.append(expression.charAt(i));
				}

				final String statement = builder.toString();

				System.out.print("Input: " + statement + "\n");

				final Scanner scanner = new Scanner(statement);

				try {
					scanner.tokenize();
				} catch (final ScannerException se) {
					System.err.println("Error: " + se.getMessage());
				}

				scanner.reformat();

				if (scanner.getStatement().length() == 0) {
					System.err
							.println("Error: Cannot construct table, no statement entered.");
				}


				final Parser parser = new Parser(scanner.getTokenStream());

				LinkedList<Token> postfixStream = null;

				try {
					parser.parse();
					parser.removeUnnecessaryParentheses();
					postfixStream = parser.getPostfixStream();
				} catch (final ParserException pe) {
					System.err.println("Error: " + pe.getMessage());
				}

				final TruthTable truthTable = new TruthTable(parser.getStatement(),
						postfixStream, TruthValue.TRUE_FALSE, true);

				final int numberOfLines = truthTable.getNumberOfLines();
				
				//System.out.println(truthTable.listSolution().toString());

				StringBuilder answers = new StringBuilder();
				int offSize = truthTable.computeRow(0).length() - truthTable.getPositionOfMainColumn();
				
			    for (int i = numberOfLines - 1; i >= 0; i--) {
					//System.out.println(truthTable.computeRow(i));
					answers.append(truthTable.computeRow(i));
				}
			    
			    //System.out.println(truthTable.getNumberOfLines());
			    
			    int position = truthTable.getPositionOfMainColumn();
			    
			    StringBuilder mainAnswer = new StringBuilder();
			    
			    for (int i = 0; i <= truthTable.getNumberOfColumns(); i++) {
			    	if(i == 0)
			    		mainAnswer.append(answers.charAt(position)); 
			    	else
			    		mainAnswer.append(answers.charAt(position));
			    	position += truthTable.getPositionOfMainColumn() + offSize;
				}
			    
		return mainAnswer.toString();

	}

	/**
	 * 
	 * @return truthTableAdaptor
	 */
	public String getTruthTable() {
		return truthTableAdaptor;
	}

	/**
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @return stateInt
	 */
	public int getIntState() {
		return stateInt;
	}

}