package com.github.terravivaproject.terraviva.social.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * TagDto class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@NoArgsConstructor
@Getter
public class TagDto extends TagRto {


    private long id;

    /**
     * Setter for the field <code>id</code>.
     *
     * @param id a long
     * @return a {@link com.github.terravivaproject.terraviva.social.entities.dto.TagDto} object
     */
    public TagDto setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TagDto setName(String name) {
        super.setName(name);
        return this;
    }

}
