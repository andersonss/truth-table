package br.ic.ufal.logic.token;

import br.ic.ufal.logic.evaluator.UnaryEvaluator;

/**
 * Negation: "~", "!".
 * 
 * @author Anderson Santos
 * 
 */
public class NegationToken extends Token implements UnaryEvaluator {

	/**
	 * @param symbol
	 * @param position
	 */
	public NegationToken(final String symbol, final int position) {
		type = Token.UNARY_OPERATOR;
		this.symbol = symbol;
		this.position = position;
		offset = (symbol.length() - 1) / 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ic.ufal.logic.token.Token#getPrecedence()
	 */
	@Override
	public int getPrecedence() {
		return 6;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.ic.ufal.logic.evaluator.UnaryEvaluator#evaluate(br.ic.ufal.logic.token
	 * .ValueToken)
	 */
	@Override
	public ValueToken evaluate(final ValueToken token) {
		// TODO Define rule using inabit
		ValueToken returnToken = null;
		if (token.getValue() == false) {
			returnToken = new ValueToken(true, token.getDisplayMethod(),
					position + offset);
		} else {
			returnToken = new ValueToken(false, token.getDisplayMethod(),
					position + offset);
		}
		return returnToken;
	}
}
