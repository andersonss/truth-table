package br.ic.ufal.logic.token;

/**
 * Start: "@".
 * 
 * @author Anderson Santos
 * 
 */
public class StartToken extends Token {

	/**
	 * @param symbol
	 */
	public StartToken(final String symbol) {
		type = Token.START;
		this.symbol = symbol;
		offset = (symbol.length() - 1) / 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ic.ufal.logic.token.Token#getPrecedence()
	 */
	@Override
	public int getPrecedence() {
		return 0;
	}
}
