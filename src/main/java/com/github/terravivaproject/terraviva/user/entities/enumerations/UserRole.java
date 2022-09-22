package com.github.terravivaproject.terraviva.user.entities.enumerations;

import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum UserRole {
    USER(1),
    ADMIN(2);

    private final int code;

    public static UserRole fromString(String role) {
        return role == null
                ? null
                : Stream.of(UserRole.values())
                .filter(r -> role.equalsIgnoreCase(r.name()))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                ErrorMessagesService.roleNotExist() + "[" + role + "]")
                );
    }

    public static UserRole fromCode(int code) {
        return Stream.of(UserRole.values())
                .filter(c -> c.getCode() == code)
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                ErrorMessagesService.roleNotExist() + "[" + code + "]")
                );
    }

    @Override
    public String toString() {
        return this.name();
    }
}
