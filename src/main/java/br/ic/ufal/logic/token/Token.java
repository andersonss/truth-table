package br.ic.ufal.logic.token;

import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * 
 * 
 * @author Anderson Santos
 * 
 */
public abstract class Token {
	public static final int UNARY_OPERATOR = 0,
							BINARY_OPERATOR = 1,
							PROPOSITION = 2,
							OPEN_PARENTHESIS = 3,
							CLOSE_PARENTHESIS = 4,
							SPACE = 5,
							START = 6,
							END = 7,
							VALUE = 8,
							CONSTANT = 9;
	protected int position = 0, offset = 0, type;
	protected String symbol;
	protected boolean isConditional = false;

	public int getPosition() {
		return position;
	}

	/**
	 * 
	 * @param position
	 */
	public void setPosition(final int position) {
		this.position = position;
	}

	/**
	 * 
	 * @return offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * 
	 * @return type
	 */
	public int getType() {
		return type;
	}

	/**
	 * 
	 * @return symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * 
	 * @return isConditional
	 */
	public boolean isConditional() {
		return isConditional;
	}

	public abstract int acceptPrecedence(TokenVisitor visitor);
}
