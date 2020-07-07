package com.antkorwin.throwable.functions;

/**
 * Java {@link Runnable} which can throw a checked exception, and
 * this exception will be wrapped in the runtime exception: {@link WrappedException}
 *
 * @author Korovin Anatoliy
 */
@FunctionalInterface
public interface ThrowableRun extends Runnable {

	/**
	 * Runs some code and wraps all checked exceptions in {@link WrappedException}
	 *
	 * @throws Throwable may throw checked exception
	 */
	void throwableRun() throws Throwable;


	/**
	 * by default this method catches checked exceptions and wrap it
	 * in the {@link WrappedException}
	 */
	@Override
	default void run() {
		try {
			throwableRun();
		} catch (RuntimeException | Error exception) {
			throw exception;
		} catch (Throwable throwable) {
			throw new WrappedException(throwable);
		}
	}
}
