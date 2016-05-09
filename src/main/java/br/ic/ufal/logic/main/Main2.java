package br.ic.ufal.logic.main;
import br.ic.ufal.logic.TruthTableConstructor;
import java.util.Scanner;

/**
 * @author Anderson Santos
 */
public class Main2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

        Scanner s = new java.util.Scanner(System.in);
		String expression;
		System.out.print("Statment: ");
		expression = s.nextLine();

        TruthTableConstructor ttc = TruthTableConstructor.getInstance();
        ttc.generateWithAnswers(expression);
        System.out.println(ttc.getMainTruthValuesAnswers(expression));
	}
}