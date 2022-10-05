package com.github.terravivaproject.terraviva.exceptions.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Dto to serialize a generic single cause error
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ErrorDto {
    @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
    @Schema(example = "04-set-2022 18:55:32")
    private final LocalDateTime timestamp = LocalDateTime.now();

    @Schema(example = "404 NOT FOUND")
    private HttpStatus status;

    @Schema(example = "The searched resource doesn't exist.")
    private String error;

    @Schema(example = "The requested resource [resource] doesn't exist")
    private String errorMessage;

    @Schema(example = "/users/lory")
    private String path;
}
