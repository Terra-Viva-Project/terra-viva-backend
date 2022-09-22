package com.github.terravivaproject.terraviva.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Exception if register an already existing user
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
@Getter
@Setter
public class UserAlreadyExistsException extends RuntimeException {
    private Map<String, List<String>> messages;

    public UserAlreadyExistsException(Map<String, List<String>> messages) {
        this.messages = messages;
    }

    public UserAlreadyExistsException(Throwable cause, Map<String, List<String>> messages) {
        super(cause);
        this.messages = messages;
    }
}
