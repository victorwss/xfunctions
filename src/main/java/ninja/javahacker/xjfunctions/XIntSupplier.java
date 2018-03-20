package ninja.javahacker.xjfunctions;

import java.util.Objects;
import java.util.function.IntSupplier;

/**
 * Represents a supplier of {@code int}-valued results that
 * might throw any exception. This is both the
 * {@code int}-producing primitive specialization of {@link XSupplier} and
 * the exception-friendly specialization of {@link IntSupplier}.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #getAsInt()}.
 *
 * @see IntSupplier
 * @see XSupplier
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XIntSupplier {

    /**
     * Gets a result.
     *
     * @return a result
     * @throws Throwable the exception that might be propagated
     */
    public int getAsInt() throws Throwable;

    /**
     * Unwraps this object into a standard {@link IntSupplier}.
     * Any exception which might be thrown by the returned supplier
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link IntSupplier}
     */
    public default IntSupplier unchecked() {
        return () -> {
            try {
                return getAsInt();
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps an standard {@link IntSupplier} into a {@code XIntSupplier}.
     * @param toWrap the {@link IntSupplier} which will be wrapped
     * @return {@code toWrap} wrapped as a {@code XIntSupplier}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static XIntSupplier wrap(IntSupplier toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::getAsInt;
    }
}
