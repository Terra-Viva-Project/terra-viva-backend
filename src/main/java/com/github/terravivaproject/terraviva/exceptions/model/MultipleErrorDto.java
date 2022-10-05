package com.github.terravivaproject.terraviva.exceptions.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Dto to serialize a generic multiple cause error
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class MultipleErrorDto {
    @DateTimeFormat(pattern = "dd-MMM-yyyy hh:mm:ss")
    @Schema(example = "04-set-2022 18:55:32")
    private final LocalDateTime timestamp = LocalDateTime.now();

    @Schema(example = "400 BAD REQUEST")
    private HttpStatus status;

    @Schema(example = "There are error in some field")
    private String error;

    @Schema(ref = "mapErrorDto")
    private Map<String, List<String>> errorMessages;

    @Schema(example = "/users/lory")
    private String path;
}
