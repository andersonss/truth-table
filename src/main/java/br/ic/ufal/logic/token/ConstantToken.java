package br.ic.ufal.logic.token;

import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * Constant: "0" or "1".
 * 
 * @author Anderson Santos
 * 
 */
public class ConstantToken extends Token implements TokenVisitable {
	private final boolean value;

	/**
	 * @param symbol
	 * @param position
	 * @param value
	 */
	public ConstantToken(final String symbol, final int position,
			final boolean value) {
		type = Token.CONSTANT;
		this.symbol = symbol;
		this.position = position;
		this.value = value;
		offset = (symbol.length() - 1) / 2;
	}
	/**
	 * @return value
	 */
	public boolean getValue() {
		return value;
	}

	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}
