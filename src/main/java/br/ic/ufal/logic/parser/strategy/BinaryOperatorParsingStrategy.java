package br.ic.ufal.logic.parser.strategy;
import br.ic.ufal.logic.parser.ParserException;
import br.ic.ufal.logic.token.Token;
/**
 * Created by Victor C. on 02/05/16.
 */
// Token.BINARY_OPERATOR
public class BinaryOperatorParsingStrategy extends ParsingStrategy{
    @Override
    public void evaluate(int tokenTwoType) throws ParserException {
        if ((tokenTwoType == Token.CLOSE_PARENTHESIS)
                || (tokenTwoType == Token.BINARY_OPERATOR)
                || (tokenTwoType == Token.END)) {
            throw new ParserException(
                    ParserException.MISSING_STATEMENT,
                    tokenOne.getPosition() + tokenOne.getSymbol().length() + 1
            );
        }
    }
}