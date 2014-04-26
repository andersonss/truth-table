package br.ic.ufal.logic.main;

import br.ic.ufal.logic.TruthTableConstructor;

/**
 * 
 * @author Anderson Santos
 * 
 */
public class Main2 {

	private static java.util.Scanner s;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String expression;

		s = new java.util.Scanner(System.in);

		System.out.print("Statment: ");

		expression = s.nextLine();

		 TruthTableConstructor.getInstance().generateWithAnswers(expression);

		// System.out.println(
//		TruthTableConstructor.getInstance().getTruthValuesAnswers(expression)
//				.toString();
		
	}
}
