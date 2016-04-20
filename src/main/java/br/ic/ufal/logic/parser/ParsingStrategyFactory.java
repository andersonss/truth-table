package br.ic.ufal.logic.parser;

import br.ic.ufal.logic.token.Token;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by victor on 20/04/16.
 */
class ParsingStrategyFactory {

    private static Map<Integer, ParsingStrategy> map;

    static {
        map = new HashMap<Integer, ParsingStrategy>();
        map.put(Token.PROPOSITION, new PropositonParsingStrategy());
    }

    public static ParsingStrategy forToken(Token token) {
        return map.get(token.getType()).withFirstToken(token);
    }
}
