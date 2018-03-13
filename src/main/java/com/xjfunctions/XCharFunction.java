package com.xjfunctions;

import java.util.Objects;

/**
 * Represents a function that accepts a char-valued argument and produces a
 * result and might throw any exception.  This is both the
 * {@code char}-consuming primitive specialization for
 * {@link XFunction} and the exception-friendly specialization of
 * {@link CharFunction}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #apply(char)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see java.util.function.Function
 * @see CharFunction
 *
 * @since XJFunction 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XCharFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable the exception that might be propagated
     */
    public R apply(char value) throws Throwable;

    /**
     * Unwraps this object into a standard {@link CharFunction}.
     * Any exception which might be thrown by the returned function
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link CharFunction}
     */
    public default CharFunction<R> unchecked() {
        return value -> {
            try {
                return apply(value);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link CharFunction} into a {@code XCharFunction}.
     * @param toWrap the {@link CharFunction} which will be wrapped
     * @param <R> the type of output of the {@code toWrap} function
     * @return toWrap wrapped as a {@code XCharFunction}.
     * @throws NullPointerException if toWrap is null
     */
    public static <R> XCharFunction<R> wrap(CharFunction<R> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::apply;
    }
}
