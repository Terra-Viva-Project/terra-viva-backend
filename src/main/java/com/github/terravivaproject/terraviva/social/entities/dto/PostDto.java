package com.github.terravivaproject.terraviva.social.entities.dto;

import com.github.terravivaproject.terraviva.resources.validations.UuidValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Schema(example = "d88e65f0-190f-47a4-84dd-768e6d3d5c2e")
    @UuidValidation
    private UUID id;

    @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
    private LocalDateTime creationDateTime;

    @NotNull
    @UuidValidation
    @Schema(example = "d88e65f0-190f-47a4-84dd-768e6d3d5c2e")
    private UUID owner;
}
