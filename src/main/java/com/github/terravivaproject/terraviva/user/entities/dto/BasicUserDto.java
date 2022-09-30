package com.github.terravivaproject.terraviva.user.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * BasicUserDto class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class BasicUserDto {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^([A-Za-z0-9@$!%*+?&]){8,}$", message = "it must be at least 8 characters long")
    @Pattern(regexp = "^(.*[@$!%*+?&].*)$", message = "must contain at least a symbol between @$!%*+?&")
    @Pattern(regexp = "^(.*[0-9].*)$", message = "It must contain at least one digit")
    @Pattern(regexp = "^(.*[a-z].*)$", message = "It must contain at least one lower case letter")
    @Pattern(regexp = "^(.*[A-Z].*)$", message = "It must contain at least one upper case letter")
    private String password;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;

}
