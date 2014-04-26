package br.ic.ufal.logic.token;

/**
 * Proposition: variables.
 * 
 * @author Anderson Santos
 * 
 */
public class PropositionToken extends Token {

	/**
	 * @param symbol
	 * @param position
	 */
	public PropositionToken(final String symbol, final int position) {
		type = Token.PROPOSITION;
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
