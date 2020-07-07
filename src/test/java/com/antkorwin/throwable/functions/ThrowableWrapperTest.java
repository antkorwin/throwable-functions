package com.antkorwin.throwable.functions;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ThrowableWrapperTest {

	@Nested
	class GetTests {

		@Test
		void wrapChecked() {
			WrappedException exc = assertThrows(WrappedException.class,
			                                    () -> {
				                                    ThrowableWrapper.get(() -> {
					                                    throw new IOException("ioio");
				                                    });
			                                    });
			// Assert
			assertThat(exc.getMessage()).contains("ioio");
			assertThat(exc.getCause()).isInstanceOf(IOException.class);
		}

		@Test
		void wrapUnchecked() {
			Exception exc = assertThrows(IndexOutOfBoundsException.class,
			                             () -> {
				                             ThrowableWrapper.get(() -> {
					                             throw new IndexOutOfBoundsException("wrong way");
				                             });
			                             });
			// Assert
			assertThat(exc.getMessage()).contains("wrong way");
		}

		@Test
		void withoutExceptions() {
			String result = ThrowableWrapper.get(() -> "123");
			assertThat(result).isEqualTo("123");
		}
	}

	@Nested
	class RunTests {

		@Test
		void wrapChecked() {
			WrappedException exc = assertThrows(WrappedException.class,
			                                    () -> {
				                                    ThrowableWrapper.run(() -> {
					                                    throw new IOException("ioio");
				                                    });
			                                    });
			// Assert
			assertThat(exc.getMessage()).contains("ioio");
			assertThat(exc.getCause()).isInstanceOf(IOException.class);
		}

		@Test
		void wrapUnchecked() {
			Exception exc = assertThrows(IndexOutOfBoundsException.class,
			                             () -> {
				                             ThrowableWrapper.run(() -> {
					                             throw new IndexOutOfBoundsException("wrong way");
				                             });
			                             });
			// Assert
			assertThat(exc.getMessage()).contains("wrong way");
		}

		@Test
		void withoutExceptions() {
			AtomicInteger atomicInteger = new AtomicInteger(0);
			ThrowableWrapper.run(atomicInteger::incrementAndGet);
			assertThat(atomicInteger.get()).isEqualTo(1);
		}

	}
}