package com.github.terravivaproject.terraviva.exceptions;

/**
 * class
 *
 * @author Gianluigi De Marco
 * @version x
 * @since 29 09 2022
 */
public class UnauthenticatedException extends RuntimeException {
    /**
     * Constructor for UnauthenticatedException.
     *
     * @param message a {@link java.lang.String} object
     */
    public UnauthenticatedException(String message) {
        super(message);
    }

    /**
     * Constructor for UnauthenticatedException.
     *
     * @param message a {@link java.lang.String} object
     * @param cause   a {@link java.lang.Throwable} object
     */
    public UnauthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for UnauthenticatedException.
     *
     * @param message            a {@link java.lang.String} object
     * @param cause              a {@link java.lang.Throwable} object
     * @param enableSuppression  a boolean
     * @param writableStackTrace a boolean
     */
    public UnauthenticatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
