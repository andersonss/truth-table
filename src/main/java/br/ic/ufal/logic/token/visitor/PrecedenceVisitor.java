package br.ic.ufal.logic.token.visitor;

import br.ic.ufal.logic.token.*;

/**
 * Created by Victor C. on 04/05/16.
 */

public class PrecedenceVisitor implements TokenVisitor{

    @Override
    public int getPrecedence(BiconditionalToken token) {
        return 2;
    }

    @Override
    public int getPrecedence(CloseParenthesisToken token) {
        return 0;
    }

    @Override
    public int getPrecedence(ConditionalToken token) {
        return 3;
    }

    @Override
    public int getPrecedence(ConjunctionToken token) {
        return 5;
    }

    @Override
    public int getPrecedence(ConstantToken token) {
        return 0;
    }

    @Override
    public int getPrecedence(EndToken token) {
        return 0;
    }

    @Override
    public int getPrecedence(ExclusiveDisjunctionToken token) {
        return 2;
    }

    @Override
    public int getPrecedence(InclusiveDisjunctionToken token) {
        return 4;
    }

    @Override
    public int getPrecedence(NegationToken token) {
        return 6;
    }

    @Override
    public int getPrecedence(OpenParenthesisToken token) {
        return 1;
    }

    @Override
    public int getPrecedence(PropositionToken token) {
        return 0;
    }

    @Override
    public int getPrecedence(SpaceToken token) {
        return 0;
    }

    @Override
    public int getPrecedence(StartToken token) {
        return 0;
    }

    @Override
    public int getPrecedence(ValueToken token) {
        return 0;
    }
}
