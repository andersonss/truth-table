package br.ic.ufal.logic.connectors;

public enum Response {
	TAUTOLOGY ("The expression provided is a tautology"),
	CONTRADICTION("The expression provided is a contradiction"),
	CONDITIONAL("The expression provided is a condititional"),
	IDENTITY("The expression provided is an identity"),
	UNDEFINED("The expression provided is undefined");	
	
	private final String humanDescription;
	
	Response(String description) {
		this.humanDescription = description;
	}
	
	String humanDescription() {
		return humanDescription;
	}
}
