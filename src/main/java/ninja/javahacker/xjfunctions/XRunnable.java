package ninja.javahacker.xjfunctions;

import java.util.Objects;

/**
 * Represents a tasks that might throw any exception.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #run()}.
 *
 * @see Runnable
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface XRunnable {

    /**
     * Executes the task.
     *
     * @throws Throwable the exception that might be propagated
     */
    public void run() throws Throwable;

    /**
     * Unwraps this object into a standard {@link Runnable}.
     * Any exception which might be thrown by the returned runnable
     * will be wrapped up into an {@link WrapperException}.
     * @return An unwrapped standard {@link Runnable}
     */
    public default Runnable unchecked() {
        return () -> {
            try {
                run();
            } catch (Throwable x) {
                throw new WrapperException(x);
            }
        };
    }

    /**
     * Wraps a standard {@link Runnable} into a {@code XRunnable}.
     * @param toWrap the {@link Runnable} which will be wrapped
     * @return {@code toWrap} wrapped as a {@code XRunnable}.
     * @throws NullPointerException if {@code toWrap} is null
     */
    public static XRunnable wrap(Runnable toWrap) {
        Objects.requireNonNull(toWrap, "toWrap");
        return toWrap::run;
    }
}
