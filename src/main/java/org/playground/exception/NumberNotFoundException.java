package org.playground.exception;

public class NumberNotFoundException extends RuntimeException {

    public NumberNotFoundException() {
    }

    public NumberNotFoundException(String message) {
        super(message);
    }
}
