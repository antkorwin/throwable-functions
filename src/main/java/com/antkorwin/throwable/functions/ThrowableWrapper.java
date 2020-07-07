package com.antkorwin.throwable.functions;

/**
 * Created on 07/07/2020
 * <p>
 * Static helper to wrap some code executions in Throwable handlers.
 *
 * @author Korovin Anatoliy
 */
public class ThrowableWrapper {

	private ThrowableWrapper() {
	}

	/**
	 * run code with checked exception handling
	 * @param runnable
	 */
	public static void run(ThrowableRun runnable) {
		runnable.run();
	}

	/**
	 * evaluate a value of supplier with checked exception handling
	 */
	public static <ResultT> ResultT get(ThrowableSupplier<ResultT> supplier) {
		return supplier.get();
	}
}
