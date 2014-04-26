package br.ic.ufal.logic.token;

import br.ic.ufal.logic.evaluator.BinaryEvaluator;

/**
 * Biconditional: "<=>", "<->".
 * 
 * @author Anderson Santos
 * 
 */
public class BiconditionalToken extends Token implements BinaryEvaluator {

	/**
	 * @param symbol
	 * @param position
	 */
	public BiconditionalToken(final String symbol, final int position) {
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
		return 2;
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
		ValueToken returnToken = null;
		if (token1.getValue() == token2.getValue()) {
			returnToken = new ValueToken(true, token1.getDisplayMethod(),
					position + offset);
		} else {
			returnToken = new ValueToken(false, token1.getDisplayMethod(),
					position + offset);
		}
		return returnToken;
	}
}
