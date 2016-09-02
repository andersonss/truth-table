package br.ic.ufal.logic.token.visitor;

/**
 * Created by Victor C. on 04/05/16.
 */
public interface TokenVisitable {
    int acceptPrecedence(TokenVisitor visitor);
}
