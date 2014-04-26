package br.ic.ufal.logic.token;

/**
 * Space: " ".
 * 
 * @author Anderson Santos
 * 
 */
public class SpaceToken extends Token {

	/**
	 * @param symbol
	 * @param position
	 */
	public SpaceToken(final String symbol, final int position) {
		type = Token.SPACE;
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
		return 0;
	}
}
