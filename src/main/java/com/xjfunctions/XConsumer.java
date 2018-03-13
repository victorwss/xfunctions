package com.xjfunctions;

import java.util.Objects;
import java.util.function.Consumer;

/**
 *
 * @since XJFunction 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XConsumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     * @throws Throwable the exception that might be propagated
     */
    public void accept(T t) throws Throwable;

    /**
     * Returns a composed {@code XConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     *
     * @return a composed {@code XConsumer} that performs in sequence this
     *     operation followed by the {@code after} operation
     *
     * @throws NullPointerException if {@code after} is null
     */
    public default XConsumer<T> andThen(XConsumer<? super T> after) {
        Objects.requireNonNull(after, "after");
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }

    /**
     * Unwraps this object into a standard {@link Consumer}.
     * Any exception which might be thrown by the returned consumer
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link Consumer}
     */
    public default Consumer<T> unchecked() {
        return e -> {
            try {
                accept(e);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link Consumer} into a {@code XConsumer}.
     * @param toWrap the {@link Consumer} which will be wrapped
     * @param <T> the type of input of the {@code toWrap} supplier
     * @return toWrap wrapped as a {@code XConsumer}.
     * @throws NullPointerException if toWrap is null
     */
    public static <T> XConsumer<T> wrap(Consumer<T> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::accept;
    }
}
