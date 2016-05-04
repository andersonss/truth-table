package br.ic.ufal.logic.parser.strategy;
import br.ic.ufal.logic.parser.ParserException;
import br.ic.ufal.logic.token.Token;
/**
 * Created by Victor C. on 02/05/16.
 */
// Token.CONSTANT
public class ConstantParsingStrategy extends ParsingStrategy{
    @Override
    public void evaluate(int tokenTwoType) throws ParserException {
        if ((tokenTwoType == Token.PROPOSITION)
                || (tokenTwoType == Token.CONSTANT)
                || (tokenTwoType == Token.UNARY_OPERATOR)
                || (tokenTwoType == Token.OPEN_PARENTHESIS)) {
            throw new ParserException(ParserException.MISSING_CONNECTIVE, tokenOne.getPosition() + 2);
        }
    }
}
