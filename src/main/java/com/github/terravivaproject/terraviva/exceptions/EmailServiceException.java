package com.github.terravivaproject.terraviva.exceptions;

/**
 * Generic Exception for the mail service
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
public class EmailServiceException extends RuntimeException {
    /**
     * Constructor for EmailServiceException.
     *
     * @param message a {@link java.lang.String} object
     */
    public EmailServiceException(String message) {
        super(message);
    }

    /**
     * Constructor for EmailServiceException.
     *
     * @param message a {@link java.lang.String} object
     * @param cause   a {@link java.lang.Throwable} object
     */
    public EmailServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
