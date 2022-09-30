package com.github.terravivaproject.terraviva.exceptions.handlers;

import com.github.terravivaproject.terraviva.exceptions.EmailServiceException;
import com.github.terravivaproject.terraviva.exceptions.EntityDoesNotExist;
import com.github.terravivaproject.terraviva.exceptions.model.ErrorDto;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Handle Entity Does Not Exist
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
@ControllerAdvice
public class EmailService_EXC_HAN {
    /**
     * entityDoesNotExistExceptionHandler.
     *
     * @param e       a {@link com.github.terravivaproject.terraviva.exceptions.EntityDoesNotExist} object
     * @param request a {@link javax.servlet.http.HttpServletRequest} object
     * @return a {@link org.springframework.http.ResponseEntity} object
     */
    @ExceptionHandler({EmailServiceException.class})
    public ResponseEntity<ErrorDto> entityDoesNotExistExceptionHandler(
            EntityDoesNotExist e,
            HttpServletRequest request) {

        return ResponseEntity.internalServerError().body(
                new ErrorDto()
                        .setError(
                                ErrorMessagesService.emailServiceError())
                        .setErrorMessage(e.getMessage())
                        .setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .setPath(
                                request.getRequestURI()
                                        .substring(
                                                request.getContextPath().length()))
        );
    }
}
