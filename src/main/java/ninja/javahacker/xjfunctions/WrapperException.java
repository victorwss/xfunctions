package ninja.javahacker.xjfunctions;

import java.util.Objects;

/**
 * Exception used to wrap-up other exceptions thrown by functional operations.
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
public class WrapperException extends RuntimeException {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Sole constructor with a cause.
     * @param cause The cause of this exception
     */
    public WrapperException(Throwable cause) {
        super(cause);
    }

    /**
     * Unwraps and rethrow the cause exception if it matches the given class type.
     * @param <T> The type of the exception that might be rethrown
     * @param exceptionClass The class of the exception that might be rethrown
     * @return This instance, if no exception was thrown
     * @throws T The exception to be thrown
     */
    public <T extends Throwable> WrapperException rethrow(Class<T> exceptionClass) throws T {
        Objects.requireNonNull(exceptionClass, "exceptionClass");
        Throwable cause = getCause();
        if (exceptionClass.isInstance(cause)) throw exceptionClass.cast(cause);
        return this;
    }

    /**
     * Rethrows {@link RuntimeException} or {@link Error} if one of them is the
     * cause of this exception. This guarantees that, should this method not
     * throw an exception, the cause must then be a checked exception.
     * @return This instance, if no exception was thrown
     * @throws RuntimeException If this is the cause of this exception
     * @throws Error If this is the cause of this exception
     */
    public WrapperException checked() {
        return rethrow(RuntimeException.class).rethrow(Error.class);
    }
}
