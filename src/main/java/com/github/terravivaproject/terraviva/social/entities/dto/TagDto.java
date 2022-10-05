package com.github.terravivaproject.terraviva.social.entities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
    @Schema(example = "04-set-2022 18:55:32")
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
