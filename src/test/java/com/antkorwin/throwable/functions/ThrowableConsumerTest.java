package com.antkorwin.throwable.functions;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ThrowableConsumerTest {

    @Test
    void accept() {
        // Assert
        ThrowableConsumer<String> consumer =
                s -> assertThat(s).isEqualTo("test");
        // Act
        consumer.accept("test");
    }

    @Test
    void error() {
        ThrowableConsumer<String> consumer =
                s -> assertThat(s).isEqualTo("test");
        // Act & assert
        Assertions.assertThrows(Error.class,
                                () -> consumer.accept("123"));
    }

    @Test
    void throwRuntimeException() {
        // Arrange
        ThrowableConsumer<String> consumer =
                s -> {
                    throw new RuntimeException("oops");
                };
        // Act
        RuntimeException exc = Assertions.assertThrows(RuntimeException.class,
                                                       () -> consumer.accept("test"));
        // Assert
        assertThat(exc.getMessage()).isEqualTo("oops");
    }

    @Test
    void throwThrowableException() {
        // Arrange
        ThrowableConsumer<String> consumer =
                s -> {
                    throw new IOException("io io");
                };
        // Act
        WrappedException exc = Assertions.assertThrows(WrappedException.class,
                                                       () -> consumer.accept("test"));
        // Assert
        assertThat(exc.getMessage()).contains("io io");
        assertThat(exc.getCause()).isInstanceOf(IOException.class);
    }
}