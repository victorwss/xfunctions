package ninja.javahacker.xjfunctions;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of one argument
 * that might throw any exception when evaluated.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #test(Object)}.
 *
 * @param <T> the type of the input to the predicate
 *
 * @see Predicate
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XPredicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     *     otherwise {@code false}
     * @throws Throwable the exception that might be propagated
     */
    public boolean test(T t) throws Throwable;

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
    public default XPredicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other, "other");
        return (t) -> test(t) && other.test(t);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     *     predicate
     */
    public default XPredicate<T> negate() {
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
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *     predicate
     * @return a composed predicate that represents the short-circuiting logical
     *     OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if {@code other} is null
     */
    public default XPredicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    /**
     * Unwraps this object into a standard {@link Predicate}.
     * Any exception which might be thrown by the returned predicate
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link Predicate}
     */
    public default Predicate<T> unchecked() {
        return t -> {
            try {
                return test(t);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link Predicate} into a {@code XPredicate}.
     * @param toWrap the {@link Predicate} which will be wrapped
     * @param <R> the type of the output of the {@code toWrap} predicate
     * @return {@code toWrap} wrapped as a {@code XPredicate}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static <R> XPredicate<R> wrap(Predicate<R> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::test;
    }
}
