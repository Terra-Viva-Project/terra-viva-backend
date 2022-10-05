package com.github.terravivaproject.terraviva.exceptions.handlers;

import com.github.terravivaproject.terraviva.exceptions.model.ErrorDto;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
