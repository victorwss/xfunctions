package ninja.javahacker.xjfunctions;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts one argument, produces a result
 * and might throw any exception.
 * This is exception-friendly specialization of {@link Function}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #apply(Object)}.</p>
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 *
 * @see Function
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XFunction<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     * @throws Throwable the exception that might be propagated
     */
    public R apply(T t) throws Throwable;

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of input to the {@code before} function, and to the
     *           composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     *     function and then applies this function
     * @throws NullPointerException if before is null
     *
     * @see #andThen(Function)
     */
    public default <V> XFunction<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     *     applies the {@code after} function
     * @throws NullPointerException if after is null
     *
     * @see #compose(Function)
     */
    public default <V> XFunction<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * Returns a function that always returns its input argument.
     *
     * @param <T> the type of the input and output objects to the function
     * @return a function that always returns its input argument
     */
    @SuppressFBWarnings("FII_USE_FUNCTION_IDENTITY")
    public static <T> XFunction<T, T> identity() {
        return t -> t;
    }

    /**
     * Unwraps this object into a standard {@link Function}.
     * Any exception which might be thrown by the returned function
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link Function}
     */
    public default Function<T, R> unchecked() {
        return t -> {
            try {
                return apply(t);
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link Function} into a {@code XFunction}.
     * @param toWrap the {@link Function} which will be wrapped
     * @param <T> the type of the input of the {@code toWrap} function
     * @param <R> the type of the output of the {@code toWrap} function
     * @return {@code toWrap} wrapped as a {@code XFunction}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static <T, R> XFunction<T, R> wrap(Function<T, R> toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::apply;
    }
}
