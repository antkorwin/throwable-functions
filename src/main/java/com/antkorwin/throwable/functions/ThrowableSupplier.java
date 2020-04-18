package com.antkorwin.throwable.functions;


import java.util.function.Supplier;


/**
 * Java {@link Supplier} which can throw a checked exception.
 *
 * @param <ResultT> type of returned result
 */
@FunctionalInterface
public interface ThrowableSupplier<ResultT> extends Supplier<ResultT> {

    /**
     * This method returns the value of expected types
     *
     * @return value of type TypeT
     * @throws Throwable this method may throw a checked exception
     */
    ResultT throwableGet() throws Throwable;

    /**
     * This method handle a checked exception and wrap it
     * in the runtime exception {@link WrappedException}
     *
     * @return the result of supplier invocation
     */
    @Override
    default ResultT get() {
        try {
            return throwableGet();
        } catch (RuntimeException | Error exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new WrappedException(throwable);
        }
    }
}