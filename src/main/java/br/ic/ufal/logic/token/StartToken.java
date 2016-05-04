package br.ic.ufal.logic.token;

import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * Start: "@".
 * 
 * @author Anderson Santos
 * 
 */
public class StartToken extends Token implements TokenVisitable {

	/**
	 * @param symbol
	 */
	public StartToken(final String symbol) {
		type = Token.START;
		this.symbol = symbol;
		offset = (symbol.length() - 1) / 2;
	}

	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}
