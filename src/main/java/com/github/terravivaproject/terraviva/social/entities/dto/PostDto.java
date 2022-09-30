package com.github.terravivaproject.terraviva.social.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * PostDto class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class PostDto extends PostRto {

    private UUID id;

    private LocalDateTime creationDateTime;

    @NotNull
    //@UUID
    // TODO: 23/09/22 implements @UUID annotation
    private UUID owner;
}
