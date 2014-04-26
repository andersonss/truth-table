package br.ic.ufal.logic.connectors;

import java.util.List;

import br.ic.ufal.logic.TruthTableConstructor;

public class Adapter {
	private Input input;
	private TruthTableConstructor ttc;

	public Adapter() {
		this.ttc = TruthTableConstructor.getInstance();
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Output solveWithAnswers() {
		return getOutputByResponse(ttc.generateWithAnswers(input
				.getExpression()));
	}

	public Output solveWithNoAnswers() {
		return getOutputByResponse(ttc.generateWithNoAnswers(input
				.getExpression()));
	}

	/**
	 * 
	 */
	public void reset() {
		this.ttc = TruthTableConstructor.getInstance();
		this.input = new Input();

	}

	private Output getOutputByResponse(List<String> printData) {

		switch (ttc.getIntState()) {
		case 0:
			return new Output(Response.TAUTOLOGY, printData);
		case 1:
			return new Output(Response.IDENTITY, printData);
		case 2:
			return new Output(Response.CONDITIONAL, printData);
		case 3:
			return new Output(Response.CONTRADICTION, printData);
		default:
			return new Output(Response.UNDEFINED, printData);
		}
	}
}