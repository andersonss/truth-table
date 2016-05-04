package br.ic.ufal.logic.token;

import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * End: "$".
 * 
 * @author Anderson Santos
 * 
 */
public class EndToken extends Token implements TokenVisitable{

	/**
	 * @param symbol
	 */
	public EndToken(final String symbol) {
		type = Token.END;
		this.symbol = symbol;
		offset = (symbol.length() - 1) / 2;
	}

	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}
