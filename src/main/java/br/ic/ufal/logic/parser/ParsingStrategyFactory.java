package br.ic.ufal.logic.parser;
import br.ic.ufal.logic.token.Token;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by victor on 20/04/16.
 */

class ParsingStrategyFactory {
    /*
    * Factory Pattern applied with the intent of instantiating and returning
    * the correct type of token to make use of its unique functionalities.
    * */
    private static Map<Integer, ParsingStrategy> map;

    /**
     * Hash with every type of Token and their respective
     * parsing-strategy type
     */
    static {
        map = new HashMap<Integer, ParsingStrategy>();
        map.put(Token.PROPOSITION, new PropositionParsingStrategy());
        map.put(Token.CONSTANT, new ConstantParsingStrategy());
        map.put(Token.UNARY_OPERATOR, new UnaryOperatorParsingStrategy());
        map.put(Token.OPEN_PARENTHESIS, new OpenParenthesisParsingStrategy());
        map.put(Token.CLOSE_PARENTHESIS, new CloseParenthesisParsingStrategy());
        map.put(Token.BINARY_OPERATOR, new BinaryOperatorParsingStrategy());
        map.put(Token.START, new StartParsingStrategy());
    }

    /**
     * @param token First Token type.
     */
    public static ParsingStrategy forToken(Token token) {
        return map.get(token.getType()).setTokenOne(token);
    }
}