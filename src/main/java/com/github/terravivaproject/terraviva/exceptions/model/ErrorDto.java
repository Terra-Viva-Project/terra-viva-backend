package com.github.terravivaproject.terraviva.exceptions.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ErrorDto {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private HttpStatus status;
    private String error;
    private List<String> errorMessages;
    private String path;
}
