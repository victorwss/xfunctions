package ninja.javahacker.xjfunctions;

import java.util.Objects;
import java.util.function.ToIntFunction;

/**
 * Represents a function that produces an int-valued result and
 * might throw any exception.  This is both the
 * {@code int}-producing primitive specialization for {@link XFunction}
 * and the exception-friendly specialization for {@link ToIntFunction}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #applyAsInt(Object)}.</p>
 *
 * @param <T> the type of the input to the function
 *
 * @see XFunction
 * @see ToIntFunction
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XToIntFunction<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     * @throws Throwable the exception that might be propagated
     */
    public int applyAsInt(T value) throws Throwable;

    /**
     * Unwraps this object into a standard {@link ToIntFunction}.
     * Any exception which might be thrown by the returned function
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link ToIntFunction}
     */
    public default ToIntFunction<T> unchecked() {
        return input -> {
            try {
                return applyAsInt(input);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link ToIntFunction} into a {@code XToIntFunction}.
     * @param toWrap the {@link ToIntFunction} which will be wrapped
     * @param <T> the type of the input of the {@code toWrap} function
     * @return {@code toWrap} wrapped as a {@code XToIntFunction}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static <T> XToIntFunction<T> wrap(ToIntFunction<T> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::applyAsInt;
    }
}
