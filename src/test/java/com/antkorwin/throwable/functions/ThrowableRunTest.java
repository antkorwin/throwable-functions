package com.antkorwin.throwable.functions;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ThrowableRunTest {

	@Test
	void runWithoutExceptions() {
		AtomicInteger counter = new AtomicInteger(0);
		ThrowableRun throwableRun = counter::incrementAndGet;
		throwableRun.run();
		assertThat(counter.get()).isEqualTo(1);
	}

	@Test
	void runChecked() {
		ThrowableRun throwableRun = () -> {
			throw new IOException("ioio");
		};
		WrappedException exc = Assertions.assertThrows(WrappedException.class, throwableRun::run);
		assertThat(exc.getMessage()).contains("ioio");
		assertThat(exc.getCause()).isInstanceOf(IOException.class);
	}

	@Test
	void runUnchecked() {
		ThrowableRun throwableRun = () -> {
			throw new IndexOutOfBoundsException("wrong way");
		};
		Exception exc = Assertions.assertThrows(IndexOutOfBoundsException.class, throwableRun::run);
		assertThat(exc.getMessage()).contains("wrong way");
	}
}