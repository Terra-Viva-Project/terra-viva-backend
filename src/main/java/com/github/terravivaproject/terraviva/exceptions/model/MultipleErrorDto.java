package com.github.terravivaproject.terraviva.exceptions.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
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
    private final LocalDateTime timestamp = LocalDateTime.now();
    private HttpStatus status;
    private String error;
    private Map<String, List<String>> errorMessages;
    private String path;
}
