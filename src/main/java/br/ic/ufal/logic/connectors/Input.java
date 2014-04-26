package br.ic.ufal.logic.connectors;


public class Input{
	
	private String expression;
	
	
	public Input() {
		
	}
	
	/**
	 * @param expression
	 */
	public Input(String expression){
		this.expression = expression;
	}
	
	/**
	 * 
	 * @param string
	 */
	public void insertStatement(String expression) {
		this.expression = expression;
	}
	
	
	public String getExpression(){
		return expression;
	}
		
}
