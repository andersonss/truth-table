package br.ic.ufal.logic.parser.strategy;
import br.ic.ufal.logic.parser.ParserException;
import br.ic.ufal.logic.token.Token;

/**
 * Created by Victor C. on 20/04/16.
 */
public abstract class ParsingStrategy {
    protected Token tokenOne;

    /**
     * Strategy Pattern introduced to deal with the execution of
     * different algorithms based on the Token's type.
     *
     * @param tokenTwoType Second Token type.
     */
    public abstract void evaluate(int tokenTwoType) throws ParserException;

    protected void setTokenOne(Token token) {
        this.tokenOne = token;
    }
}