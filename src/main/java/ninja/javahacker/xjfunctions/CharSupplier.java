package ninja.javahacker.xjfunctions;

/**
 * Represents a supplier of {@code char}-valued results.  This is the
 * {@code char}-producing primitive specialization of
 * {@link java.util.function.Supplier Supplier}.
 *
 * <p>There is no requirement that a distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is afunctional interface
 * whose functional method is {@link #getAsChar()}.
 *
 * @see java.util.function.Supplier
 * @see java.util.function.IntSupplier
 *
 * @since XJFunctions 1.0
 * @author Victor Williams Stafusa da Silva
 */
@FunctionalInterface
public interface CharSupplier {

    /**
     * Gets a result.
     *
     * @return a result
     */
    public char getAsChar();
}