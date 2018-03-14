package com.xjfunctions;

import java.util.Objects;
import java.util.function.ToLongFunction;

/**
 * Represents a function that produces a long-valued result and
 * might throw any exception.  This is both the
 * {@code long}-producing primitive specialization for {@link XFunction}
 * and the exception-friendly specialization for {@link ToLongFunction}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #applyAsLong(Object)}.
 *
 * @param <T> the type of the input to the function
 *
 * @see XFunction
 * @see ToLongFunction
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XToLongFunction<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable the exception that might be propagated
     */
    public long applyAsLong(T value) throws Throwable;

    /**
     * Unwraps this object into a standard {@link ToLongFunction}.
     * Any exception which might be thrown by the returned function
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link ToLongFunction}
     */
    public default ToLongFunction<T> unchecked() {
        return input -> {
            try {
                return applyAsLong(input);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link ToLongFunction} into a {@code XToLongFunction}.
     * @param toWrap the {@link ToLongFunction} which will be wrapped
     * @param <T> the type of the input of the {@code toWrap} function
     * @return {@code toWrap} wrapped as a {@code XToLongFunction}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static <T> XToLongFunction<T> wrap(ToLongFunction<T> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::applyAsLong;
    }
}
