package com.example.annotationtest.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
class InvalidEmailExceptionTest {

    private InvalidEmailException exception;
    private InvalidEmailException exception2;
    private String email = "emial@email.com";

    @BeforeEach
    void setUp() {
        exception = new InvalidEmailException();
        exception2 = new InvalidEmailException(email);
    }

    @Test
    void getMessage() {
        assertThat(exception.getMessage()).isEqualTo("wrong email");
        assertThat(exception2.getMessage()).isEqualTo("wrong email " + email);
    }

    @Test
    void testToString() {
        assertThat(exception.toString()).isEqualTo("invalidEmailException");
        assertThat(exception2.toString()).isEqualTo("invalidEmailException " + email);
    }

}