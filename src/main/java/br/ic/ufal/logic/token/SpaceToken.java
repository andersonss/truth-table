package br.ic.ufal.logic.token;

import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * Space: " ".
 * 
 * @author Anderson Santos
 * 
 */
public class SpaceToken extends Token implements TokenVisitable{

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

	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}
