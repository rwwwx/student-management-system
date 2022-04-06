package com.example.annotationtest.exception;

public class InvalidSubjectNameException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Invalid data for student";
    }

    @Override
    public String toString() {
        return "Invalid data for student";
    }

}
