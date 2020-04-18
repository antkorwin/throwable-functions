package com.antkorwin.throwable.functions;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ThrowableFunctionTest {

    @Test
    void func() {
        ThrowableFunction<Integer, String> func = arg -> arg + "*2 = " + arg * 2;
        assertThat(func.apply(2)).isEqualTo("2*2 = 4");
    }

    @Test
    void throwUnchecked() {
        // Arrange
        ThrowableFunction<Integer, String> func = in -> {
            throw new RuntimeException("oops");
        };
        // Act
        RuntimeException exc =
                Assertions.assertThrows(RuntimeException.class,
                                        () -> func.apply(123));
        // Assert
        assertThat(exc.getMessage()).isEqualTo("oops");
    }

    @Test
    void throwChecked() {
        // Arrange
        ThrowableFunction<Integer, String> func = in -> {
            throw new IOException("oops");
        };
        // Act
        WrappedException exc =
                Assertions.assertThrows(WrappedException.class,
                                        () -> func.apply(123));
        // Assert
        assertThat(exc.getCause()).isInstanceOf(IOException.class);
        assertThat(exc.getMessage()).contains("oops");
    }
}