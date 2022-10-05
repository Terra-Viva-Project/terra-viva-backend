package com.github.terravivaproject.terraviva.exceptions.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
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

    private final LocalDateTime timestamp = LocalDateTime.now();

    @Schema(example = "XXX STATUS_MESSAGE")
    private HttpStatus status;

    @Schema(example = "General error message")
    private String error;

    @Schema(example = "Specific error message")
    private String errorMessage;

    @Schema(example = "/users/lory")
    private String path;
}
