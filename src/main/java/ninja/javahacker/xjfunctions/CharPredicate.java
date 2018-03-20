package ninja.javahacker.xjfunctions;

import java.util.Objects;

/**
 * Represents a predicate (char-valued function) of one {@code char}-valued
 * argument. This is the {@code char}-consuming primitive type specialization
 * of {@link java.util.function.Predicate Predicate}.
 *
 * <p>This is afunctional interface
 * whose functional method is {@link #test(char)}.
 *
 * @see java.util.function.Predicate
 * @see java.util.function.IntPredicate
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface CharPredicate {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value the input argument
     * @return {@code true} if the input argument matches the predicate,
     *     otherwise {@code false}
     */
    public boolean test(char value);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this
     *     predicate
     * @return a composed predicate that represents the short-circuiting logical
     *     AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if {@code other} is null
     */
    public default CharPredicate and(CharPredicate other) {
        Objects.requireNonNull(other, "other");
        return (value) -> test(value) && other.test(value);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     *     predicate
     */
    public default CharPredicate negate() {
        return (value) -> !test(value);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *     predicate
     * @return a composed predicate that represents the short-circuiting logical
     *     OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if {@code other} is null
     */
    public default CharPredicate or(CharPredicate other) {
        Objects.requireNonNull(other, "other");
        return (value) -> test(value) || other.test(value);
    }
}