package com.example.annotationtest.exception;

public class InvalidEmailException extends RuntimeException {

    private String invalidEmail;

    @Override
    public String getMessage() {
        return invalidEmail == null ? "wrong email" : ("wrong email" + " " + invalidEmail);
    }

    @Override
    public String toString() {
        return invalidEmail == null ? "invalidEmailException" : ("invalidEmailException" + " " + invalidEmail);
    }

    public InvalidEmailException(String invalidEmail) {
        this.invalidEmail = invalidEmail;
    }

    public InvalidEmailException() {

    }

}
