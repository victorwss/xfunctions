package com.xjfunctions;

import java.util.Objects;
import java.util.function.ToDoubleFunction;

/**
 * Represents a function that produces a double-valued result and
 * might throw any exception.  This is both the
 * {@code double}-producing primitive specialization for {@link XFunction}
 * and the exception-friendly specialization for {@link ToDoubleFunction}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #applyAsDouble(Object)}.
 *
 * @param <T> the type of the input to the function
 *
 * @see XFunction
 * @see ToDoubleFunction
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XToDoubleFunction<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable the exception that might be propagated
     */
    public double applyAsDouble(T value) throws Throwable;

    /**
     * Unwraps this object into a standard {@link ToDoubleFunction}.
     * Any exception which might be thrown by the returned function
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link ToDoubleFunction}
     */
    public default ToDoubleFunction<T> unchecked() {
        return input -> {
            try {
                return applyAsDouble(input);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link ToDoubleFunction} into a {@code XToDoubleFunction}.
     * @param toWrap the {@link ToDoubleFunction} which will be wrapped
     * @param <T> the type of the input of the {@code toWrap} function
     * @return {@code toWrap} wrapped as a {@code XToDoubleFunction}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static <T> XToDoubleFunction<T> wrap(ToDoubleFunction<T> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::applyAsDouble;
    }
}
