package ninja.javahacker.xjfunctions;

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Represents a predicate (boolean-valued function) of two arguments
 * that might throw any exception when evaluated. This is both
 * the two-arity specialization of {@link XPredicate} and the
 * exception-friendly specialization of {@link BiPredicate}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #test(Object, Object)}.
 *
 * @param <T> the type of the first argument to the predicate
 * @param <U> the type of the second argument the predicate
 *
 * @see XPredicate
 * @see BiPredicate
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XBiPredicate<T, U> {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     * @return {@code true} if the input arguments match the predicate,
     *     otherwise {@code false}
     * @throws Throwable the exception that might be propagated
     */
    public boolean test(T t, U u) throws Throwable;

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
    public default XBiPredicate<T, U> and(BiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other, "other");
        return (T t, U u) -> test(t, u) && other.test(t, u);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     *     predicate
     */
    public default XBiPredicate<T, U> negate() {
        return (T t, U u) -> !test(t, u);
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
    public default XBiPredicate<T, U> or(BiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other, "other");
        return (T t, U u) -> test(t, u) || other.test(t, u);
    }

    /**
     * Unwraps this object into a standard {@link BiPredicate}.
     * Any exception which might be thrown by the returned predicate
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link BiPredicate}
     */
    public default BiPredicate<T, U> unchecked() {
        return (t, u) -> {
            try {
                return test(t, u);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link BiPredicate} into a {@code XBiPredicate}.
     * @param toWrap the {@link BiPredicate} which will be wrapped
     * @param <T> the type of the first input of the {@code toWrap} predicate
     * @param <U> the type of the second input of the {@code toWrap} predicate
     * @return {@code toWrap} wrapped as a {@code XBiPredicate}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static <T, U> XBiPredicate<T, U> wrap(BiPredicate<T, U> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::test;
    }
}
