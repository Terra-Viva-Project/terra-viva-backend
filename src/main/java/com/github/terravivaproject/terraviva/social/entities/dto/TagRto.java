package com.github.terravivaproject.terraviva.social.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class TagRto {

    @NotNull
    @NotBlank
    private String name;

    /**
     * Setter for the field <code>name</code>.
     *
     * @param name a {@link java.lang.String} object
     * @return a {@link com.github.terravivaproject.terraviva.social.entities.dto.TagRto} object
     */
    public TagRto setName(String name) {
        this.name = name;
        return this;
    }

}
