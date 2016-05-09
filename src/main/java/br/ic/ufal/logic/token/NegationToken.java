package br.ic.ufal.logic.token;
import br.ic.ufal.logic.evaluator.UnaryEvaluator;
import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * Negation: "~", "!".
 * 
 * @author Anderson Santos
 * 
 */
public class NegationToken extends Token implements UnaryEvaluator, TokenVisitable{

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
	 * @see
	 * br.ic.ufal.logic.evaluator.UnaryEvaluator#evaluate(br.ic.ufal.logic.token
	 * .ValueToken)
	 */
	@Override
	public ValueToken evaluate(final ValueToken token) {
		return evaluateToken(token, null);
	}
	
	@Override
	public boolean logicalOperation(ValueToken token1, ValueToken token2) {
		return !token1.getValue();
	}

	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}
