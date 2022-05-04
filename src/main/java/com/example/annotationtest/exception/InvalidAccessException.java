package com.example.annotationtest.exception;

public class InvalidAccessException extends RuntimeException {

    @Override
    public String getMessage() {
        return "invalid action, you may have no permission for this method";
    }

    @Override
    public String toString() {
        return "InvalidAccessException";
    }
}
