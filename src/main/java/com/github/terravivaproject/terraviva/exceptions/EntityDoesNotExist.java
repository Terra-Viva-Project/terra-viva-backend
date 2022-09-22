package com.github.terravivaproject.terraviva.exceptions;

/**
 * Generic exception for entity/resource does not exist
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
public class EntityDoesNotExist extends RuntimeException {
    public EntityDoesNotExist() {
    }

    public EntityDoesNotExist(String message) {
        super(message);
    }

    public EntityDoesNotExist(Throwable cause) {
        super(cause);
    }

    public EntityDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }
}

