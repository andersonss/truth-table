package br.ic.ufal.logic.token;

/**
 * Constant: "0" or "1".
 * 
 * @author Anderson Santos
 * 
 */
public class ConstantToken extends Token {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ic.ufal.logic.token.Token#getPrecedence()
	 */
	@Override
	public int getPrecedence() {
		return 0;
	}

	/**
	 * @return value
	 */
	public boolean getValue() {
		return value;
	}
}
