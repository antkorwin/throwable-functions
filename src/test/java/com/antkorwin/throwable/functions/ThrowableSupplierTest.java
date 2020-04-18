package com.antkorwin.throwable.functions;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ThrowableSupplierTest {

    @Test
    void supplier() {
        ThrowableSupplier<String> supplier = () -> "test";
        // Act & assert
        assertThat(supplier.get()).isEqualTo("test");
    }

    @Test
    void throwUnchecked() {
        ThrowableSupplier<String> supplier = () -> {
            throw new RuntimeException("oops");
        };
        // Act
        RuntimeException exc =
                Assertions.assertThrows(RuntimeException.class, supplier::get);
        // Assert
        assertThat(exc.getMessage()).isEqualTo("oops");
    }

    @Test
    void throwChecked() {
        ThrowableSupplier<String> supplier = () -> {
            throw new IOException("oops");
        };
        // Act
        WrappedException exc =
                Assertions.assertThrows(WrappedException.class, supplier::get);
        // Assert
        assertThat(exc.getMessage()).contains("oops");
        assertThat(exc.getCause()).isInstanceOf(IOException.class);
    }
}