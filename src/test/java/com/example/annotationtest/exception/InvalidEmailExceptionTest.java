package com.example.annotationtest.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
class InvalidEmailExceptionTest {

    private InvalidEmailException exception;

    @BeforeEach
    void setUp() {
        exception = new InvalidEmailException();
    }

    @Test
    void getMessage() {
        assertThat(exception.getMessage()).isEqualTo("Invalid data for student");
    }

    @Test
    void testToString() {
        assertThat(exception.toString()).isEqualTo("Invalid data for student");
    }

}