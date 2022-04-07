package com.example.annotationtest.exception;

public class InvalidIdException extends RuntimeException {

    @Override
    public String getMessage() {
        return "invalid id, entity with this id may not exists";
    }

    @Override
    public String toString() {
        return "InvalidIdException";
    }

}
