package com.antkorwin.throwable.functions;

import java.util.function.Consumer;

/**
 * Created on 18/04/2020
 * <p>
 * Java {@link Consumer} which can throw an checked exception, and
 * this exception will be wrapped in the runtime exception: {@link WrappedException}
 *
 * @author Korovin Anatoliy
 */
@FunctionalInterface
public interface ThrowableConsumer<T> extends Consumer<T> {

    void throwableAccept(T t) throws Throwable;

    @Override
    default void accept(T t) {
        try {
            throwableAccept(t);
        } catch (RuntimeException | Error exception) {
            throw exception;
        } catch (Throwable throwable) {
            throw new WrappedException(throwable);
        }
    }
}
