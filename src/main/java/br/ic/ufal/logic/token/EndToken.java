package br.ic.ufal.logic.token;

/**
 * End: "$".
 * 
 * @author Anderson Santos
 * 
 */
public class EndToken extends Token {

	/**
	 * @param symbol
	 */
	public EndToken(final String symbol) {
		type = Token.END;
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
