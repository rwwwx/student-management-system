package com.example.annotationtest.exception;

public class InvalidIdException extends RuntimeException {

    @Override
    public String getMessage() {
        return "invalid id, student with this id may not exists";
    }

    @Override
    public String toString() {
        return "InvalidIdException";
    }

}
