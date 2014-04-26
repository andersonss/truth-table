package br.ic.ufal.logic.token;

/**
 * Open parenthesis: "(".
 * 
 * @author Anderson Santos
 * 
 */
public class OpenParenthesisToken extends Token {

	/**
	 * @param symbol
	 * @param position
	 */
	public OpenParenthesisToken(final String symbol, final int position) {
		type = Token.OPEN_PARENTHESIS;
		this.symbol = symbol;
		this.position = position;
		offset = (symbol.length() - 1) / 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ic.ufal.logic.token.Token#getPrecedence()
	 */
	@Override
	public int getPrecedence() {
		return 1;
	}
}
