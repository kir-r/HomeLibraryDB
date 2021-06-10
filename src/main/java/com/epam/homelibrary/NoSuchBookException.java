package com.epam.homelibrary;

public class NoSuchBookException extends Exception {
    public NoSuchBookException(String message) {
        super(message);
    }
}
