package ninja.javahacker.xjfunctions;

import java.util.Objects;

/**
 * Represents an operation that accepts a single {@code char}-valued argument and
 * returns no result, but might throw any exception. This is both the
 * primitive type specialization of {@link java.util.function.Consumer Consumer}
 * for {@code char} and
 * the exception-friendly specialization of {@link CharConsumer}.
 * Unlike most other functional interfaces,
 * {@code XCharConsumer} is expected to operate via side-effects.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.</p>
 *
 * <p>This is a functional interface
 * whose functional method is {@link #accept(char)}.</p>
 *
 * @see java.util.function.Consumer
 * @see CharConsumer
 * @see XConsumer
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XCharConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     * @throws Throwable the exception that might be propagated
     */
    public void accept(char value) throws Throwable;

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
    public default XCharConsumer andThen(CharConsumer after) {
        Objects.requireNonNull(after);
        return (char t) -> {
            accept(t);
            after.accept(t);
        };
    }

    /**
     * Unwraps this object into a standard {@link CharConsumer}.
     * Any exception which might be thrown by the returned consumer
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link CharConsumer}
     */
    public default CharConsumer unchecked() {
        return value -> {
            try {
                accept(value);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps an standard {@link CharConsumer} into a {@code XCharConsumer}.
     * @param toWrap the {@link CharConsumer} which will be wrapped
     * @return {@code toWrap} wrapped as a {@code XLongConsumer}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static XCharConsumer wrap(CharConsumer toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::accept;
    }
}
