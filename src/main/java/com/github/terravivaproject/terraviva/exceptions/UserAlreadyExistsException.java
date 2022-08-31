package com.github.terravivaproject.terraviva.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAlreadyExistsException extends IllegalArgumentException {
    private List<String> messages;

    public UserAlreadyExistsException(List<String> messages) {
        this.messages = messages;
    }

    public UserAlreadyExistsException(Throwable cause, List<String> messages) {
        super(cause);
        this.messages = messages;
    }
}
