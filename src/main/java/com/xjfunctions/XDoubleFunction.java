package com.xjfunctions;

import java.util.Objects;
import java.util.function.DoubleFunction;

/**
 * Represents a function that accepts a double-valued argument and produces a
 * result and might throw any exception.  This is both the
 * {@code double}-consuming primitive specialization for
 * {@link XFunction} and the exception-friendly specialization of
 * {@link DoubleFunction}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #apply(double)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see java.util.function.Function
 * @see java.util.function.DoubleFunction
 *
 * @since XJFunction 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XDoubleFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable the exception that might be propagated
     */
    public R apply(double value) throws Throwable;

    /**
     * Unwraps this object into a standard {@link DoubleFunction}.
     * Any exception which might be thrown by the returned function
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link DoubleFunction}
     */
    public default DoubleFunction<R> unchecked() {
        return value -> {
            try {
                return apply(value);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link DoubleFunction} into a {@code XDoubleFunction}.
     * @param toWrap the {@link DoubleFunction} which will be wrapped
     * @param <R> the type of output of the {@code toWrap} function
     * @return toWrap wrapped as a {@code XDoubleFunction}.
     * @throws NullPointerException if toWrap is null
     */
    public static <R> XDoubleFunction<R> wrap(DoubleFunction<R> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::apply;
    }
}
