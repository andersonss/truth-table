package br.ic.ufal.logic.token;

import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * Proposition: variables.
 * 
 * @author Anderson Santos
 * 
 */
public class PropositionToken extends Token implements TokenVisitable {

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

	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}
