package br.ic.ufal.logic.connectors;

import java.util.List;

public class Output {
	private Response response;
	private List<String> printData;
	
	/**
	 * 
	 * @param expression
	 */
	
	public Output() {
		
	}
	public Output(Response response, List<String> printData){
		this.response = response;
		this.printData = printData;
		
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
	
	public List<String> getPrintData() {
		return printData;
	}
	
	public void setPrintData(List<String> printData) {
		this.printData = printData;
	}
}
