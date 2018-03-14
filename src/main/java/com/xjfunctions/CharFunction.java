package com.xjfunctions;

/**
 * Represents a function that accepts a char-valued argument and produces a
 * result.  This is the {@code char}-consuming primitive specialization for
 * {@link java.util.function.Function Function}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #apply(char)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see java.util.function.Function
 * @see java.util.function.IntFunction
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface CharFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    public R apply(char value);
}