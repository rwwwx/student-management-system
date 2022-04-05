package com.example.annotationtest.exception;

public class invalidEmailException extends RuntimeException {

    @Override
    public String getMessage() {
        return "wrong email";
    }

    @Override
    public String toString() {
        return "invalidEmailException";
    }

}
