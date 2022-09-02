package com.github.terravivaproject.terraviva.exceptions;

public class ConfirmationJustExpiredException extends RuntimeException {
    public ConfirmationJustExpiredException() {
    }

    public ConfirmationJustExpiredException(String message) {
        super(message);
    }

    public ConfirmationJustExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
