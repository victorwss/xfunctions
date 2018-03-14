package com.xjfunctions;

import java.util.Objects;
import java.util.function.DoubleConsumer;

/**
 * Represents an operation that accepts a single {@code double}-valued argument and
 * returns no result, but might throw any exception. This is both the
 * primitive type specialization of {@link java.util.function.Consumer Consumer}
 * for {@code double} and
 * the exception-friendly specialization of {@link java.util.function.DoubleConsumer DoubleConsumer}.
 * Unlike most other functional interfaces,
 * {@code XDoubleConsumer} is expected to operate via side-effects.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #accept(double)}.
 *
 * @see java.util.function.Consumer
 * @see java.util.function.DoubleConsumer
 * @see XConsumer
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XDoubleConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     * @throws Throwable the exception that might be propagated
     */
    public void accept(double value) throws Throwable;

    /**
     * Returns a composed {@code XDoubleConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code DoubleConsumer} that performs in sequence this
     *     operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    public default XDoubleConsumer andThen(DoubleConsumer after) {
        Objects.requireNonNull(after);
        return (double t) -> {
            accept(t);
            after.accept(t);
        };
    }

    /**
     * Unwraps this object into a standard {@link DoubleConsumer}.
     * Any exception which might be thrown by the returned consumer
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link DoubleConsumer}
     */
    public default DoubleConsumer unchecked() {
        return value -> {
            try {
                accept(value);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps an standard {@link DoubleConsumer} into a {@code XDoubleConsumer}.
     * @param toWrap the {@link DoubleConsumer} which will be wrapped
     * @return {@code toWrap} wrapped as a {@code XDoubleConsumer}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static XDoubleConsumer wrap(DoubleConsumer toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::accept;
    }
}
