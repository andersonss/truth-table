package br.ic.ufal.logic.token;

import br.ic.ufal.logic.TruthValue;
import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * 
 * 
 * @author Anderson Santos
 * 
 */
public class ValueToken extends Token implements TokenVisitable{
	private final boolean value;
	private final int displayMethod;

	/**
	 * @param value
	 * @param displayMethod
	 */
	public ValueToken(final boolean value, final int displayMethod) {
		type = Token.VALUE;
		this.value = value;
		this.displayMethod = displayMethod;
		symbol = TruthValue.getTruthValueString(value, displayMethod);
		offset = (symbol.length() - 1) / 2;
	}

	/**
	 * @param value
	 * @param displayMethod
	 * @param position
	 */
	public ValueToken(final boolean value, final int displayMethod,
			final int position) {
		this(value, displayMethod);
		this.position = position;
	}

	/**
	 * @return value
	 */
	public boolean getValue() {
		return value;
	}

	/**
	 * @return displayMethod
	 */
	public int getDisplayMethod() {
		return displayMethod;
	}

	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}
