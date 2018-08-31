package ninja.javahacker.xjfunctions;

import java.util.Objects;
import java.util.function.LongFunction;

/**
 * Represents a function that accepts a long-valued argument and produces a
 * result and might throw any exception.  This is both the
 * {@code long}-consuming primitive specialization for
 * {@link XFunction} and the exception-friendly specialization of
 * {@link LongFunction}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #apply(long)}.</p>
 *
 * @param <R> the type of the result of the function
 *
 * @see java.util.function.Function
 * @see java.util.function.LongFunction
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XLongFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable the exception that might be propagated
     */
    public R apply(long value) throws Throwable;

    /**
     * Unwraps this object into a standard {@link LongFunction}.
     * Any exception which might be thrown by the returned function
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link LongFunction}
     */
    public default LongFunction<R> unchecked() {
        return value -> {
            try {
                return apply(value);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link LongFunction} into a {@code XLongFunction}.
     * @param toWrap the {@link LongFunction} which will be wrapped
     * @param <R> the type of the output of the {@code toWrap} function
     * @return {@code toWrap} wrapped as a {@code XLongFunction}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static <R> XLongFunction<R> wrap(LongFunction<R> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::apply;
    }
}
