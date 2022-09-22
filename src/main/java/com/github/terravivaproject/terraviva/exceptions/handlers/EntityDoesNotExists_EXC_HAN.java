package com.github.terravivaproject.terraviva.exceptions.handlers;

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
public class EntityDoesNotExists_EXC_HAN {
    @ExceptionHandler({EntityDoesNotExist.class})
    public ResponseEntity<ErrorDto> entityDoesNotExistExceptionHandler(
            EntityDoesNotExist e,
            HttpServletRequest request) {

        return new ResponseEntity<>(
                new ErrorDto()
                        .setError(
                                ErrorMessagesService.resourceNotExist())
                        .setErrorMessage(e.getMessage())
                        .setStatus(HttpStatus.NOT_FOUND)
                        .setPath(
                                request.getRequestURI()
                                        .substring(
                                                request.getContextPath().length())),
                HttpStatus.NOT_FOUND
        );
    }
}
