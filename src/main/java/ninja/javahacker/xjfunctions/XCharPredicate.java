package ninja.javahacker.xjfunctions;

import java.util.Objects;

/**
 * Represents a predicate (char-valued function) of one {@code char}-valued
 * argument that might throw any exception when evaluated.
 * This is both the {@code char}-consuming primitive type specialization of
 * {@link XPredicate} and the eception-firendly specialization of
 * {@link CharPredicate}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #test(char)}.</p>
 *
 * @see XPredicate
 * @see CharPredicate
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XCharPredicate {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value the input argument
     * @return {@code true} if the input argument matches the predicate,
     *     otherwise {@code false}
     * @throws Throwable the exception that might be propagated
     */
    public boolean test(char value) throws Throwable;

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.</p>
     *
     * @param other a predicate that will be logically-ANDed with this
     *     predicate
     * @return a composed predicate that represents the short-circuiting logical
     *     AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if {@code other} is null
     */
    public default XCharPredicate and(CharPredicate other) {
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
    public default XCharPredicate negate() {
        return (t) -> !test(t);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.</p>
     *
     * @param other a predicate that will be logically-ORed with this
     *     predicate
     * @return a composed predicate that represents the short-circuiting logical
     *     OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if {@code other} is null
     */
    public default XCharPredicate or(CharPredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) || other.test(value);
    }

    /**
     * Unwraps this object into a standard {@link CharPredicate}.
     * Any exception which might be thrown by the returned predicate
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link CharPredicate}
     */
    public default CharPredicate unchecked() {
        return value -> {
            try {
                return test(value);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link CharPredicate} into a {@code XCharPredicate}.
     * @param toWrap the {@link CharPredicate} which will be wrapped
     * @return {@code toWrap} wrapped as a {@code XCharPredicate}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static XCharPredicate wrap(CharPredicate toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::test;
    }
}
