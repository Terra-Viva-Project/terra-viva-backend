package com.github.terravivaproject.terraviva.exceptions;

public class EntityDoesNotExist extends RuntimeException {
    public EntityDoesNotExist() {
    }

    public EntityDoesNotExist(String message) {
        super(message);
    }

    public EntityDoesNotExist(Throwable cause) {
        super(cause);
    }
}

