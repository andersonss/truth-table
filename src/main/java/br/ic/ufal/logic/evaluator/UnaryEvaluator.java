package br.ic.ufal.logic.evaluator;

import br.ic.ufal.logic.token.ValueToken;

/**
 * 
 * @author Anderson Santos
 * 
 */
public interface UnaryEvaluator {
	public ValueToken evaluate(ValueToken token);
}
