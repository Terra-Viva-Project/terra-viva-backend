package com.github.terravivaproject.terraviva.exceptions;

/**
 * desc
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 23 09 2022
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }
}
