package br.ic.ufal.logic.parser;
import br.ic.ufal.logic.token.Token;
/**
 * Created by Victor C. on 02/05/16.
 */
// Token.OPEN_PARENTHESIS
public class OpenParenthesisParsingStrategy extends ParsingStrategy{
    @Override
    public void evaluate(int tokenTwoType) throws ParserException {
        if (tokenTwoType == Token.CLOSE_PARENTHESIS) {
            throw new ParserException(ParserException.MISSING_STATEMENT_IN_PARENTHESES, tokenOne.getPosition() + 2);
        } else if (tokenTwoType == Token.BINARY_OPERATOR) {
            throw new ParserException(ParserException.MISSING_STATEMENT, tokenOne.getPosition() + 2);
        } else if (tokenTwoType == Token.END) {
            throw new ParserException(ParserException.ILLEGAL_USE_OF_PARENTHESES, tokenOne.getPosition() + 1);
        }
    }
}