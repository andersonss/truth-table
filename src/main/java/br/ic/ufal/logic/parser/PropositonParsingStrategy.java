package br.ic.ufal.logic.parser;

import br.ic.ufal.logic.token.Token;

/**
 * Created by victor on 20/04/16.
 */
public class PropositonParsingStrategy implements ParsingStrategy {
    private Token token1;

    @Override
    public void evaluate(int token2Type) throws ParserException {
        if ((token2Type == Token.PROPOSITION)
                || (token2Type == Token.CONSTANT)
                || (token2Type == Token.UNARY_OPERATOR)
                || (token2Type == Token.OPEN_PARENTHESIS)) {
            throw new ParserException(
                    ParserException.MISSING_CONNECTIVE,
                    token1.getPosition() + 2);
        }
    }

    @Override
    public ParsingStrategy withFirstToken(Token token) {
        this.token1 = token;
    }
}
