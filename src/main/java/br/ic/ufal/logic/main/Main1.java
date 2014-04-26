package br.ic.ufal.logic.main;

import java.util.ArrayList;
import java.util.List;

import br.ic.ufal.logic.TruthTableConstructor;

/**
 * 
 * @author Anderson Santos
 * 
 */
public class Main1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> expression = new ArrayList<String>();

		// expression.add("(A v B) -> (A v B)");
		// expression.add("(A ^ B) -> (A ^ B) ^ C <-> ((P->Q)^P)->Q");
		// expression.add("A v (A ^ B)");
		expression.add("((P->Q)^P)->Q");
		// expression.add("(A^B)^(A^C)^(BvA)->A");
		expression.add("((A -> B) ^ A) -> B");
		expression.add("A ^ ~A");

		// expression.add("((A->B)^(~AvC))v(B)");

		// TruthTableConstructor.getInstance().generateWithAnswers(
		// expression.get(0));

		TruthTableConstructor ttc = TruthTableConstructor.getInstance();
		ttc.generateWithAnswers(expression.get(1));
		// System.out.println(ttc.getMainTruthValuesAnswers(expression.get(1)));

		// ttc.getTruthValuesAnswers(expression.get(3));
		// System.out.println(ttc.generateWithNoAnswers(expression.get(4)));
		// System.out.print(ttc.getState());

		// String[][] truthValue = ttc.getTruthValuesAnswers(expression.get(3));
		//
		// for (int i = 0; i < truthValue.length; i++) {
		// System.out.println(truthValue[i][0]);
		// }

		// System.out.println(ttc.generateWithNoAnswers(expression.get(0)));

		// for(String s : ttc.generateWithNoAnswers(expression.get(0)))
		// System.out.println(s);

	}
}
