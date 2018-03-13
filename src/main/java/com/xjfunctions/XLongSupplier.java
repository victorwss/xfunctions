package com.xjfunctions;

import java.util.Objects;
import java.util.function.LongSupplier;

/**
 * Represents a supplier of {@code long}-valued results that
 * might throw any exception. This is both the
 * {@code long}-producing primitive specialization of {@link XSupplier} and
 * the exception-friendly specialization of {@link LongSupplier}.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #getAsLong()}.
 *
 * @see LongSupplier
 * @see XSupplier
 *
 * @since XJFunction 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XLongSupplier {

    /**
     * Gets a result.
     *
     * @return a result
     * @throws Throwable the exception that might be propagated
     */
    public long getAsLong() throws Throwable;

    /**
     * Unwraps this object into a standard {@link LongSupplier}.
     * Any exception which might be thrown by the returned supplier
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link LongSupplier}
     */
    public default LongSupplier unchecked() {
        return () -> {
            try {
                return getAsLong();
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps an standard {@link LongSupplier} into a {@code XLongSupplier}.
     * @param toWrap the {@link LongSupplier} which will be wrapped
     * @return toWrap wrapped as a {@code XLongSupplier}.
     * @throws NullPointerException if toWrap is null
     */
    public static XLongSupplier wrap(LongSupplier toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::getAsLong;
    }
}
