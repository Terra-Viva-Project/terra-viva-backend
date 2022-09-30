package com.github.terravivaproject.terraviva.social.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private String name;
}
