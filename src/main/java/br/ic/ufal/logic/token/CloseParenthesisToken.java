package br.ic.ufal.logic.token;

import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * Close parenthesis: ")".
 * 
 * @author anderson
 * 
 */
public class CloseParenthesisToken extends Token implements TokenVisitable {

	public CloseParenthesisToken(final String symbol, final int position) {
		type = CLOSE_PARENTHESIS;
		this.symbol = symbol;
		this.position = position;
		offset = (symbol.length() - 1) / 2;
	}

	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}
