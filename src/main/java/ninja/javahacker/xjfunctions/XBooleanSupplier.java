package ninja.javahacker.xjfunctions;

import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * Represents a supplier of {@code boolean}-valued results that
 * might throw any exception. This is both the
 * {@code boolean}-producing primitive specialization of {@link XSupplier} and
 * the exception-friendly specialization of {@link BooleanSupplier}.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.</p>
 *
 * <p>This is a functional interface
 * whose functional method is {@link #getAsBoolean()}.</p>
 *
 * @see BooleanSupplier
 * @see XSupplier
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XBooleanSupplier {

    /**
     * Gets a result.
     *
     * @return a result
     * @throws Throwable the exception that might be propagated
     */
    public boolean getAsBoolean() throws Throwable;

    /**
     * Unwraps this object into a standard {@link BooleanSupplier}.
     * Any exception which might be thrown by the returned supplier
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link BooleanSupplier}
     */
    public default BooleanSupplier unchecked() {
        return () -> {
            try {
                return getAsBoolean();
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps an standard {@link BooleanSupplier} into a {@code XBooleanSupplier}.
     * @param toWrap the {@link BooleanSupplier} which will be wrapped
     * @return {@code toWrap} wrapped as a {@code XBooleanSupplier}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static XBooleanSupplier wrap(BooleanSupplier toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::getAsBoolean;
    }
}
