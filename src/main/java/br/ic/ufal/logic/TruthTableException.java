package br.ic.ufal.logic;

/**
 * 
 * 
 * @author Anderson Santos
 * 
 */
public abstract class TruthTableException extends Exception {
	private static final long serialVersionUID = 1L;
	protected String message;
	protected int messageType;

	@Override
	public String getMessage() {
		return message;
	}
}
