package ninja.javahacker.xjfunctions;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts two input arguments, returns no
 * result and might throw any exception.
 * This is both the two-arity specialization of {@link XConsumer} and the
 * exception-friendly specialization of {@link BiConsumer}.
 * Unlike most other functional interfaces, {@code XBiConsumer} is expected
 * to operate via side-effects.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #accept(Object, Object)}.
 *
 * @param <T> the type of the first argument to the operation
 * @param <U> the type of the second argument to the operation
 *
 * @see java.util.function.Consumer
 * @see java.util.function.BiConsumer
 * @see XConsumer
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XBiConsumer<T, U> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     * @throws Throwable the exception that might be propagated
     */
    public void accept(T t, U u) throws Throwable;

    /**
     * Returns a composed {@code XBiConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code XBiConsumer} that performs in sequence this
     *     operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    public default XBiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after, "after");

        return (l, r) -> {
            accept(l, r);
            after.accept(l, r);
        };
    }

    /**
     * Unwraps this object into a standard {@link BiConsumer}.
     * Any exception which might be thrown by the returned consumer
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link BiConsumer}
     */
    public default BiConsumer<T, U> unchecked() {
        return (t, u) -> {
            try {
                accept(t, u);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link BiConsumer} into a {@code XBiConsumer}.
     * @param toWrap the {@link BiConsumer} which will be wrapped
     * @param <T> the type of the first input of the {@code toWrap} consumer
     * @param <U> the type of the second input of the {@code toWrap} consumer
     * @return {@code toWrap} wrapped as a {@code XBiConsumer}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static <T, U> XBiConsumer<T, U> wrap(BiConsumer<T, U> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::accept;
    }
}
