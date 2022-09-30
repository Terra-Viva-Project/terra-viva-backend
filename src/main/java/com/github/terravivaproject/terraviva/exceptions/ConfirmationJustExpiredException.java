package com.github.terravivaproject.terraviva.exceptions;

/**
 * Exception for a just expired confirmation token
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
public class ConfirmationJustExpiredException extends RuntimeException {
    /**
     * Constructor for ConfirmationJustExpiredException.
     */
    public ConfirmationJustExpiredException() {
    }

    /**
     * Constructor for ConfirmationJustExpiredException.
     *
     * @param message a {@link java.lang.String} object
     */
    public ConfirmationJustExpiredException(String message) {
        super(message);
    }

    /**
     * Constructor for ConfirmationJustExpiredException.
     *
     * @param message a {@link java.lang.String} object
     * @param cause   a {@link java.lang.Throwable} object
     */
    public ConfirmationJustExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
