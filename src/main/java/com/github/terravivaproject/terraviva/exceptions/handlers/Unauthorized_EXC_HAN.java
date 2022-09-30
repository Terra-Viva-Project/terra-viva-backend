package com.github.terravivaproject.terraviva.exceptions.handlers;

import com.github.terravivaproject.terraviva.exceptions.ConfirmationJustExpiredException;
import com.github.terravivaproject.terraviva.exceptions.model.ErrorDto;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Handle Confirmation Just Expired Exception
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
@ControllerAdvice
public class Unauthorized_EXC_HAN {
    /**
     * confirmationJustExpiredExceptionHandler.
     *
     * @param e       a {@link com.github.terravivaproject.terraviva.exceptions.ConfirmationJustExpiredException} object
     * @param request a {@link javax.servlet.http.HttpServletRequest} object
     * @return a {@link org.springframework.http.ResponseEntity} object
     */
    @ExceptionHandler({ConfirmationJustExpiredException.class})
    public ResponseEntity<ErrorDto> confirmationJustExpiredExceptionHandler(
            ConfirmationJustExpiredException e,
            HttpServletRequest request) {

        return new ResponseEntity<>(
                new ErrorDto()
                        .setError(ErrorMessagesService.unauthorized())
                        .setErrorMessage(e.getMessage())
                        .setStatus(HttpStatus.FORBIDDEN)
                        .setPath(
                                request.getRequestURI()
                                        .substring(
                                                request.getContextPath().length())),
                HttpStatus.FORBIDDEN
        );
    }
}
