package ninja.javahacker.xjfunctions;

import java.util.Objects;
import java.util.function.IntFunction;

/**
 * Represents a function that accepts an int-valued argument and produces a
 * result and might throw any exception.  This is both the
 * {@code int}-consuming primitive specialization for
 * {@link XFunction} and the exception-friendly specialization of
 * {@link IntFunction}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #apply(int)}.</p>
 *
 * @param <R> the type of the result of the function
 *
 * @see java.util.function.Function
 * @see java.util.function.IntFunction
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XIntFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable the exception that might be propagated
     */
    public R apply(int value) throws Throwable;

    /**
     * Unwraps this object into a standard {@link IntFunction}.
     * Any exception which might be thrown by the returned function
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link IntFunction}
     */
    public default IntFunction<R> unchecked() {
        return value -> {
            try {
                return apply(value);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link IntFunction} into a {@code XIntFunction}.
     * @param toWrap the {@link IntFunction} which will be wrapped
     * @param <R> the type of the output of the {@code toWrap} function
     * @return {@code toWrap} wrapped as a {@code XIntFunction}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static <R> XIntFunction<R> wrap(IntFunction<R> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::apply;
    }
}
