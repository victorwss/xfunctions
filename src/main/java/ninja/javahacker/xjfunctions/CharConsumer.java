package ninja.javahacker.xjfunctions;

import java.util.Objects;

/**
 * Represents an operation that accepts a single {@code char}-valued argument and
 * returns no result.  This is the primitive type specialization of
 * {@link java.util.function.Consumer Consumer} for {@code char}.
 * Unlike most other functional interfaces,
 * {@code CharConsumer} is expected to operate via side-effects.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #accept(char)}.
 *
 * @see java.util.function.Consumer
 * @see java.util.function.IntConsumer
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface CharConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     */
    public void accept(char value);

    /**
     * Returns a composed {@code CharConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code CharConsumer} that performs in sequence this
     *     operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    public default CharConsumer andThen(CharConsumer after) {
        Objects.requireNonNull(after, "after");
        return (char t) -> {
            accept(t);
            after.accept(t);
        };
    }
}
