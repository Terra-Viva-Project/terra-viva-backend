package com.github.terravivaproject.terraviva.user.entities.enumerations;

import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * UserRole class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Getter
@AllArgsConstructor
public enum UserRole {
    USER(1),
    ADMIN(2);

    /**
     * fromString.
     *
     * @param role a {@link java.lang.String} object
     * @return a {@link com.github.terravivaproject.terraviva.user.entities.enumerations.UserRole} object
     */
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

    /**
     * fromCode.
     *
     * @param code a int
     * @return a {@link com.github.terravivaproject.terraviva.user.entities.enumerations.UserRole} object
     */
    public static UserRole fromCode(int code) {
        return Stream.of(UserRole.values())
                .filter(c -> c.getCode() == code)
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                ErrorMessagesService.roleNotExist() + "[" + code + "]")
                );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.name();
    }
}
