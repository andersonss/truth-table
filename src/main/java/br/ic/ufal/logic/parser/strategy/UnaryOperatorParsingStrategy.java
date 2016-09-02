package br.ic.ufal.logic.parser.strategy;
import br.ic.ufal.logic.parser.ParserException;
import br.ic.ufal.logic.token.Token;
/**
 * Created by Victor C. on 02/05/16.
 */
// Token.UNARY_OPERATOR
public class UnaryOperatorParsingStrategy extends ParsingStrategy{
    @Override
    public void evaluate(int tokenTwoType) throws ParserException {
        if ((tokenTwoType == Token.CLOSE_PARENTHESIS)
                || (tokenTwoType == Token.BINARY_OPERATOR)
                || (tokenTwoType == Token.END)) {
            throw new ParserException(ParserException.MISSING_STATEMENT, tokenOne.getPosition() + 2);
        }
    }
}
