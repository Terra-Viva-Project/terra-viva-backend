package com.github.terravivaproject.terraviva.exceptions;

/**
 * desc
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 23 09 2022
 */
public class UnauthorizedException extends RuntimeException {
    /**
     * Constructor for UnauthorizedException.
     *
     * @param message a {@link java.lang.String} object
     */
    public UnauthorizedException(String message) {
        super(message);
    }

    /**
     * Constructor for UnauthorizedException.
     *
     * @param message a {@link java.lang.String} object
     * @param cause   a {@link java.lang.Throwable} object
     */
    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for UnauthorizedException.
     *
     * @param cause a {@link java.lang.Throwable} object
     */
    public UnauthorizedException(Throwable cause) {
        super(cause);
    }
}
