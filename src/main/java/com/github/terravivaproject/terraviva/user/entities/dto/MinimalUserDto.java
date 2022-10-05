package com.github.terravivaproject.terraviva.user.entities.dto;

import com.github.terravivaproject.terraviva.resources.validations.UuidValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Schema(example = "d88e65f0-190f-47a4-84dd-768e6d3d5c2e")
    private UUID id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    @Schema(example = "lorenzo")
    private String username;

    @NotNull
    @NotBlank
    @Email
    @Size(min = 5, max = 255)
    @Schema(example = "lorenzo.defrancesco@gmail.com")
    private String email;
}
