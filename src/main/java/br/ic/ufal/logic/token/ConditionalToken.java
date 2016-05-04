package br.ic.ufal.logic.token;

import br.ic.ufal.logic.evaluator.BinaryEvaluator;
import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * Conditional: "=>", "->".
 * 
 * @author Anderson Santos
 * 
 */
public class ConditionalToken extends Token implements BinaryEvaluator, TokenVisitable {

	/**
	 * @param symbol
	 * @param position
	 */
	public ConditionalToken(final String symbol, final int position) {
		type = BINARY_OPERATOR;
		this.symbol = symbol;
		this.position = position;
		this.isConditional = true;
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
	public ValueToken evaluate(ValueToken token1, ValueToken token2) {
		// TODO Define rule using inabit
		ValueToken returnToken = null;
		if ((token1.getValue() == true) && (token2.getValue() == false)) {
			returnToken = new ValueToken(false, token1.getDisplayMethod(),
					position + offset);
		} else {
			returnToken = new ValueToken(true, token1.getDisplayMethod(),
					position + offset);
		}
		return returnToken;
	}

	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}