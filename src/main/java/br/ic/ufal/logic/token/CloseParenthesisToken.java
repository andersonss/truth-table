package br.ic.ufal.logic.token;

/**
 * Close parenthesis: ")".
 * 
 * @author anderson
 * 
 */
public class CloseParenthesisToken extends Token {

	public CloseParenthesisToken(final String symbol, final int position) {
		type = CLOSE_PARENTHESIS;
		this.symbol = symbol;
		this.position = position;
		offset = (symbol.length() - 1) / 2;
	}

	@Override
	public int getPrecedence() {
		return 0;
	}
}
