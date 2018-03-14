package com.xjfunctions;

import java.util.Objects;

/**
 * Represents a function that produces a char-valued result and
 * might throw any exception.  This is both the
 * {@code char}-producing primitive specialization for {@link XFunction}
 * and the exception-friendly specialization for {@link ToCharFunction}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #applyAsChar(Object)}.
 *
 * @param <T> the type of the input to the function
 *
 * @see XFunction
 * @see ToCharFunction
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XToCharFunction<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable the exception that might be propagated
     */
    public char applyAsChar(T value) throws Throwable;

    /**
     * Unwraps this object into a standard {@link ToCharFunction}.
     * Any exception which might be thrown by the returned function
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link ToCharFunction}
     */
    public default ToCharFunction<T> unchecked() {
        return input -> {
            try {
                return applyAsChar(input);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link ToCharFunction} into a {@code XToCharFunction}.
     * @param toWrap the {@link ToCharFunction} which will be wrapped
     * @param <T> the type of the input of the {@code toWrap} function
     * @return {@code toWrap} wrapped as a {@code XToCharFunction}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static <T> XToCharFunction<T> wrap(ToCharFunction<T> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::applyAsChar;
    }
}
