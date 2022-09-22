package com.github.terravivaproject.terraviva.exceptions;

/**
 * Exception for a just expired confirmation token
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
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
