package br.ic.ufal.logic.token;

import br.ic.ufal.logic.evaluator.BinaryEvaluator;

/**
 * Inclusive disjunction: "v".
 * 
 * @author Anderson Santos
 * 
 */
public class InclusiveDisjunctionToken extends Token implements BinaryEvaluator {

	/**
	 * @param symbol
	 * @param position
	 */
	public InclusiveDisjunctionToken(final String symbol, final int position) {
		type = Token.BINARY_OPERATOR;
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
		return 4;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.ic.ufal.logic.evaluator.BinaryEvaluator#evaluate(br.ic.ufal.logic.
	 * token.ValueToken, br.ic.ufal.logic.token.ValueToken)
	 */
	@Override
	public ValueToken evaluate(final ValueToken token1, final ValueToken token2) {
		// TODO Define rule using inabit
		ValueToken returnToken = null;
		if ((token1.getValue() == false) && (token2.getValue() == false)) {
			returnToken = new ValueToken(false, token1.getDisplayMethod(),
					position + offset);
		} else {
			returnToken = new ValueToken(true, token1.getDisplayMethod(),
					position + offset);
		}
		return returnToken;
	}
}
