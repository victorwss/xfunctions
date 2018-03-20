package ninja.javahacker.xjfunctions;

/**
 * Represents a function that produces a char-valued result.  This is the
 * {@code char}-producing primitive specialization for
 * {@link java.util.function.Function Function}.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #applyAsChar(Object)}.
 *
 * @param <T> the type of the input to the function
 *
 * @see java.util.function.Function
 * @see java.util.function.ToIntFunction
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface ToCharFunction<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    public char applyAsChar(T value);
}