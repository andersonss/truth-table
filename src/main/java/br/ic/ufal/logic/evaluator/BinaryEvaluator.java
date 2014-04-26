package br.ic.ufal.logic.evaluator;

import br.ic.ufal.logic.token.ValueToken;

/**
 * 
 * @author Anderson Santos
 * 
 */
public interface BinaryEvaluator {
	public ValueToken evaluate(ValueToken token1, ValueToken token2);
}
