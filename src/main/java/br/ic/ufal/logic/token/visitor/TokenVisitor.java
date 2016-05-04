package br.ic.ufal.logic.token.visitor;
import br.ic.ufal.logic.token.*;

/**
 * Created by Victor C. on 04/05/16.
 */
public interface TokenVisitor {

    int getPrecedence(BiconditionalToken token);

    int getPrecedence(CloseParenthesisToken token);

    int getPrecedence(ConditionalToken token);

    int getPrecedence(ConjunctionToken token);

    int getPrecedence(ConstantToken token);

    int getPrecedence(EndToken token);

    int getPrecedence(ExclusiveDisjunctionToken token);

    int getPrecedence(InclusiveDisjunctionToken token);

    int getPrecedence(NegationToken token);

    int getPrecedence(OpenParenthesisToken token);

    int getPrecedence(PropositionToken token);

    int getPrecedence(SpaceToken token);

    int getPrecedence(StartToken token);

    int getPrecedence(ValueToken token);

}