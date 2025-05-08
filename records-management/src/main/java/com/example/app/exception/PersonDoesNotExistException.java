package com.example.app.exception;

public class PersonDoesNotExistException extends RuntimeException {
    public PersonDoesNotExistException() {
        super("Person does not exist");
    }

    public PersonDoesNotExistException(String message) {
        super(message);
    }
}
