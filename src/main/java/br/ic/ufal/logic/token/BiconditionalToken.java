package br.ic.ufal.logic.token;
import br.ic.ufal.logic.evaluator.BinaryEvaluator;
import br.ic.ufal.logic.token.visitor.TokenVisitable;
import br.ic.ufal.logic.token.visitor.TokenVisitor;

/**
 * Biconditional: "<=>", "<->".
 * 
 * @author Anderson Santos
 * 
 */
public class BiconditionalToken extends Token implements BinaryEvaluator, TokenVisitable {

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
	 * @see
	 * br.ic.ufal.logic.evaluator.BinaryEvaluator#evaluate(br.ic.ufal.logic.
	 * token.ValueToken, br.ic.ufal.logic.token.ValueToken)
	 */
	@Override
	public ValueToken evaluate(final ValueToken token1, final ValueToken token2) {
		return evaluateToken(token1, token2);
	}
	
	@Override
	public boolean logicalOperation(ValueToken token1, ValueToken token2) {
		return token1.getValue() == token2.getValue();
	}
	
	@Override
	public int acceptPrecedence(TokenVisitor visitor) {
		return visitor.getPrecedence(this);
	}
}
