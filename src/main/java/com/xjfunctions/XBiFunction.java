package com.xjfunctions;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * Represents a function that accepts two arguments, produces a result
 * and might throw any exception.
 * This is both the two-arity specialization of {@link XFunction} and the
 * exception-friendly specialization of {@link BiFunction}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 * @param <R> the type of the result of the function
 *
 * @see java.util.function.BiFunction
 *
 * @since XJFunction 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XBiFunction<T, U, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     * @throws Throwable the exception that might be propagated
     */
    public R apply(T t, U u) throws Throwable;

    /**
     * Returns a composed {@code XBiFunction} that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of output of the {@code after} function, and of the
     *     composed function
     * @param after the {@code XBiFunction} to apply after this function is applied
     * @return a composed function that first applies this function and then
     *     applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    public default <V> XBiFunction<T, U, V> andThen(XFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after, "after");
        return (T t, U u) -> after.apply(apply(t, u));
    }

    /**
     * Unwraps this object into a standard {@link BiFunction}.
     * Any exception which might be thrown by the returned function
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link BiFunction}
     */
    public default BiFunction<T, U, R> unchecked() {
        return (t, u) -> {
            try {
                return apply(t, u);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link BiFunction} into a {@code XBiFunction}.
     * @param toWrap the {@link BiFunction} which will be wrapped
     * @param <T> the type of first input of the {@code toWrap} function
     * @param <U> the type of second input of the {@code toWrap} function
     * @param <R> the type of output of the {@code toWrap} function
     * @return toWrap wrapped as a {@code XBiFunction}.
     * @throws NullPointerException if toWrap is null
     */
    public static <T, U, R> XBiFunction<T, U, R> wrap(BiFunction<T, U, R> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::apply;
    }
}
