package com.github.terravivaproject.terraviva.social.entities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * TagRto class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class TagRto {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    @Schema(example = "Pomodori")
    private String name;
}
