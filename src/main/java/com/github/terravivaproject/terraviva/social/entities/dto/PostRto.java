package com.github.terravivaproject.terraviva.social.entities.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * PostRto class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Schema(description = "This payload is used to create a new post")
public class PostRto {

    @NotNull
    @ArraySchema(
            schema = @Schema(required = true),
            arraySchema = @Schema(
                    maxLength = 100,
                    minLength = 2,
                    example = "[\"Pomodori\", \"Raccolta\"]"))
    private Collection<String> tags;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 1000)
    @Schema(
            required = true,
            example = "I miei pomodori sono Bellissimi... e questa sera me li mangio")
    private String message;
}
