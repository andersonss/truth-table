package br.ic.ufal.logic.token;

import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * Open parenthesis: "(".
 * 
 * @author Anderson Santos
 * 
 */
public class OpenParenthesisToken extends Token implements TokenVisitable {

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

	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}
