package br.ic.ufal.logic.token;

import br.ic.ufal.logic.evaluator.BinaryEvaluator;
import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * Conjunction: "&", "^".
 * 
 * @author Anderson Santos
 * 
 */
public class ConjunctionToken extends Token implements BinaryEvaluator, TokenVisitable {

	/**
	 * @param symbol
	 * @param position
	 */
	public ConjunctionToken(final String symbol, final int position) {
		type = Token.BINARY_OPERATOR;
		this.symbol = symbol;
		this.position = position;
		offset = (symbol.length() - 1) / 2;
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
		final boolean token1Value = token1.getValue();
		if (token1Value == token2.getValue()) {
			returnToken = new ValueToken(token1Value,
					token1.getDisplayMethod(), position + offset);
		} else {
			returnToken = new ValueToken(false, token1.getDisplayMethod(),
					position + offset);
		}
		return returnToken;
	}

	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}