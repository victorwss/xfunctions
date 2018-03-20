package ninja.javahacker.xjfunctions;

import java.util.Objects;
import java.util.function.DoubleSupplier;

/**
 * Represents a supplier of {@code double}-valued results that
 * might throw any exception. This is both the
 * {@code double}-producing primitive specialization of {@link XSupplier} and
 * the exception-friendly specialization of {@link DoubleSupplier}.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #getAsDouble()}.
 *
 * @see DoubleSupplier
 * @see XSupplier
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XDoubleSupplier {

    /**
     * Gets a result.
     *
     * @return a result
     * @throws Throwable the exception that might be propagated
     */
    public double getAsDouble() throws Throwable;

    /**
     * Unwraps this object into a standard {@link DoubleSupplier}.
     * Any exception which might be thrown by the returned supplier
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link DoubleSupplier}
     */
    public default DoubleSupplier unchecked() {
        return () -> {
            try {
                return getAsDouble();
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps an standard {@link DoubleSupplier} into a {@code XDoubleSupplier}.
     * @param toWrap the {@link DoubleSupplier} which will be wrapped
     * @return {@code toWrap} wrapped as a {@code XDoubleSupplier}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static XDoubleSupplier wrap(DoubleSupplier toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::getAsDouble;
    }
}
