package br.ic.ufal.logic.parser;

import br.ic.ufal.logic.token.Token;

/**
 * Created by victor on 20/04/16.
 */
public interface ParsingStrategy {
    void evaluate(int token2Type) throws ParserException;

    ParsingStrategy withFirstToken(Token token1Type);
}
