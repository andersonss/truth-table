package br.ic.ufal.logic.main;

import br.ic.ufal.logic.connectors.*;


public class Main3 {
	public static void main(String[] args) {
		Input input = new Input();
		//
		//input.insertStatement("(A v B) -> (A v B)");
		input.insertStatement("(A ^ B) -> (A ^ B) ^ C <-> ((P->Q)^P)->Q");
		//input.insertStatement("A v (A ^ B)");
//		input.insertStatement("A -> B");

		
		Adapter adapter = new Adapter();
		adapter.setInput(input);
		
		Output output = adapter.solveWithAnswers();
//		System.out.println(output.getResponse().toString());
		//System.out.println(output.getPrintData());
		
	}
}
