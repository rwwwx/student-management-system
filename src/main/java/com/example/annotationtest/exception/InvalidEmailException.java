package com.example.annotationtest.exception;

public class InvalidEmailException extends RuntimeException {

    @Override
    public String getMessage() {
        return "wrong email";
    }

    @Override
    public String toString() {
        return "invalidEmailException";
    }

}
