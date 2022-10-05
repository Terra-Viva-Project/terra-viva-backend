package com.github.terravivaproject.terraviva.exceptions.handlers;

import com.github.terravivaproject.terraviva.exceptions.model.ErrorDto;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Handle Illegal Argument Exception
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
@ControllerAdvice
public class IllegalArgument_EXC_HAN {
    /**
     * illegalArgumentExceptionHandler.
     *
     * @param e       a {@link java.lang.IllegalArgumentException} object
     * @param request a {@link javax.servlet.http.HttpServletRequest} object
     * @return a {@link org.springframework.http.ResponseEntity} object
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "An illegal or inappropriate argument is used for the request")
    @Operation(responses = @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)))
    public ResponseEntity<ErrorDto> illegalArgumentExceptionHandler(
            IllegalArgumentException e,
            HttpServletRequest request) {

        return ResponseEntity
                .badRequest()
                .body(new ErrorDto()
                        .setError(ErrorMessagesService.requestError())
                        .setErrorMessage(e.getMessage())
                        .setStatus(HttpStatus.BAD_REQUEST)
                        .setPath(
                                request.getRequestURI()
                                        .substring(
                                                request.getContextPath().length())
                        )
                );
    }
}
