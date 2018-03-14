package com.xjfunctions;

import java.util.Objects;
import java.util.function.LongConsumer;

/**
 * Represents an operation that accepts a single {@code long}-valued argument and
 * returns no result, but might throw any exception. This is both the
 * primitive type specialization of {@link java.util.function.Consumer Consumer}
 * for {@code long} and
 * the exception-friendly specialization of {@link java.util.function.LongConsumer LongConsumer}.
 * Unlike most other functional interfaces,
 * {@code XLongConsumer} is expected to operate via side-effects.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #accept(long)}.
 *
 * @see java.util.function.Consumer
 * @see LongConsumer
 * @see XConsumer
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XLongConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     * @throws Throwable the exception that might be propagated
     */
    public void accept(long value) throws Throwable;

    /**
     * Returns a composed {@code XLongConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code LongConsumer} that performs in sequence this
     *     operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    public default XLongConsumer andThen(LongConsumer after) {
        Objects.requireNonNull(after);
        return (long t) -> {
            accept(t);
            after.accept(t);
        };
    }

    /**
     * Unwraps this object into a standard {@link LongConsumer}.
     * Any exception which might be thrown by the returned consumer
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link LongConsumer}
     */
    public default LongConsumer unchecked() {
        return value -> {
            try {
                accept(value);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps an standard {@link LongConsumer} into a {@code XLongConsumer}.
     * @param toWrap the {@link LongConsumer} which will be wrapped
     * @return {@code toWrap} wrapped as a {@code XLongConsumer}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static XLongConsumer wrap(LongConsumer toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::accept;
    }
}
