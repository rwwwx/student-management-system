package com.example.annotationtest.exception;

public class invalidSubjectNameException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Invalid data for student";
    }

    @Override
    public String toString() {
        return "Invalid data for student";
    }

}
