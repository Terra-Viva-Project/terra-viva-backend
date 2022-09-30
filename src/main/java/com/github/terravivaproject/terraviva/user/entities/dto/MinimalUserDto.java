package com.github.terravivaproject.terraviva.user.entities.dto;

import com.github.terravivaproject.terraviva.resources.validations.UuidValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * class
 *
 * @author Gianluigi De Marco
 * @version x
 * @since 30 09 2022
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class MinimalUserDto {
    @UuidValidation
    private UUID id;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    @Email
    private String email;
}
