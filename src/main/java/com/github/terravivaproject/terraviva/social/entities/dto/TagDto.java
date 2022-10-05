package com.github.terravivaproject.terraviva.social.entities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * TagDto class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class TagDto extends TagRto {
    @Schema(example = "5")
    private long id;

    private LocalDateTime updatedOn;

    /**
     * {@inheritDoc}
     */
    @Override
    public TagDto setName(String name) {
        super.setName(name);
        return this;
    }
}
