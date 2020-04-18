package com.antkorwin.throwable.functions;


/**
 * Created on 17/04/2020
 * <p>
 * Use to wrap checked exceptions in runtime exceptions
 *
 * @author Korovin Anatoliy
 */
public class WrappedException extends RuntimeException {

    public WrappedException(Throwable throwable) {
        super(throwable);
    }
}
