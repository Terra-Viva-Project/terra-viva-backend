package com.github.terravivaproject.terraviva.exceptions;

/**
 * Generic exception for entity/resource does not exist
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
public class EntityDoesNotExist extends RuntimeException {
    /**
     * Constructor for EntityDoesNotExist.
     */
    public EntityDoesNotExist() {
    }

    /**
     * Constructor for EntityDoesNotExist.
     *
     * @param message a {@link java.lang.String} object
     */
    public EntityDoesNotExist(String message) {
        super(message);
    }

    /**
     * Constructor for EntityDoesNotExist.
     *
     * @param cause a {@link java.lang.Throwable} object
     */
    public EntityDoesNotExist(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for EntityDoesNotExist.
     *
     * @param message a {@link java.lang.String} object
     * @param cause   a {@link java.lang.Throwable} object
     */
    public EntityDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }
}

