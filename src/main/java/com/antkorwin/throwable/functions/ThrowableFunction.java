package com.antkorwin.throwable.functions;


import java.util.function.Function;


/**
 * Java {@link Function} with an ability to throw checked exceptions in the body code
 *
 * @param <InputArgumentT> the type of the input for function
 * @param <ResultT> the type of the result
 */
@FunctionalInterface
public interface ThrowableFunction<InputArgumentT, ResultT> extends Function<InputArgumentT, ResultT> {

    /**
     * This method can throw a checked exception
     *
     * @param t the value of input argument for function
     * @return the result of the function invocation
     * @throws Throwable this function may throw an exception
     */
    ResultT throwableApply(InputArgumentT t) throws Throwable;

    /**
     * This method handle all exceptions and wrap checked exceptions
     * in the runtime with the type {@link WrappedException}
     *
     * @param t the input argument for of function
     * @return the result of the function invocation
     */
    @Override
    default ResultT apply(InputArgumentT t) {
        try {
            return throwableApply(t);
        } catch (RuntimeException | Error exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new WrappedException(throwable);
        }
    }
}
