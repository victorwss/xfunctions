package ninja.javahacker.xjfunctions;

import java.util.Objects;

/**
 * Represents a supplier of {@code char}-valued results that
 * might throw any exception. This is both the
 * {@code char}-producing primitive specialization of {@link XSupplier} and
 * the exception-friendly specialization of {@link CharSupplier}.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #getAsChar()}.
 *
 * @see CharSupplier
 * @see XSupplier
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XCharSupplier {

    /**
     * Gets a result.
     *
     * @return a result
     * @throws Throwable the exception that might be propagated
     */
    public char getAsChar() throws Throwable;

    /**
     * Unwraps this object into a standard {@link CharSupplier}.
     * Any exception which might be thrown by the returned supplier
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link CharSupplier}
     */
    public default CharSupplier unchecked() {
        return () -> {
            try {
                return getAsChar();
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps an standard {@link CharSupplier} into a {@code XCharSupplier}.
     * @param toWrap the {@link CharSupplier} which will be wrapped
     * @return {@code toWrap} wrapped as a {@code XCharSupplier}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static XCharSupplier wrap(CharSupplier toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::getAsChar;
    }
}
