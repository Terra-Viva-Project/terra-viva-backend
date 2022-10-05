package com.github.terravivaproject.terraviva.user.entities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

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
    @Size(min = 1, max = 255)
    @Schema(example = "lory.defi")
    private String username;

    @NotNull
    @NotBlank
    @Email
    @Size(min = 1, max = 255)
    @Schema(example = "lorenzo.defrancesco@gmail.com")
    private String email;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^([A-Za-z0-9@$!%*+?&]){8,}$", message = "it must be at least 8 characters long")
    @Pattern(regexp = "^(.*[@$!%*+?&].*)$", message = "must contain at least a symbol between @$!%*+?&")
    @Pattern(regexp = "^(.*[0-9].*)$", message = "It must contain at least one digit")
    @Pattern(regexp = "^(.*[a-z].*)$", message = "It must contain at least one lower case letter")
    @Pattern(regexp = "^(.*[A-Z].*)$", message = "It must contain at least one upper case letter")
    @Size(min = 1, max = 60)
    @Schema(example = "Ab+12345")
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    @Schema(example = "Lorenzo")
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    @Schema(example = "De Francesco")
    private String lastName;

}
