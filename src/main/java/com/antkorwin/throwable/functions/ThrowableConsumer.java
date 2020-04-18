package com.antkorwin.throwable.functions;

import java.util.function.Consumer;

/**
 * Created on 18/04/2020
 * <p>
 * Java {@link Consumer} which can throw a checked exception, and
 * this exception will be wrapped in the runtime exception: {@link WrappedException}
 *
 * @author Korovin Anatoliy
 */
@FunctionalInterface
public interface ThrowableConsumer<InputArgumentT> extends Consumer<InputArgumentT> {

    /**
     * perform this method on the given argument,
     * this method can throw a checked exception
     *
     * @param argument the input argument
     * @throws Throwable may throw checked exception
     */
    void throwableAccept(InputArgumentT argument) throws Throwable;

    /**
     * by default this method catches checked exceptions and wrap it
     * in the {@link WrappedException}
     *
     * @param argument the input argument
     */
    @Override
    default void accept(InputArgumentT argument) {
        try {
            throwableAccept(argument);
        } catch (RuntimeException | Error exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new WrappedException(throwable);
        }
    }
}
