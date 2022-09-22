package com.github.terravivaproject.terraviva.exceptions.handlers;

import com.github.terravivaproject.terraviva.exceptions.UserAlreadyExistsException;
import com.github.terravivaproject.terraviva.exceptions.model.MultipleErrorDto;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Handle user Already Exists Exception Handler
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
@ControllerAdvice
public class UserAlreadyExist_EXC_HAN {
    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<MultipleErrorDto> userAlreadyExistsExceptionHandler(
            UserAlreadyExistsException e,
            HttpServletRequest request) {

        return ResponseEntity
                .badRequest()
                .body(new MultipleErrorDto()
                        .setError(ErrorMessagesService.userAlreadyExist())
                        .setErrorMessages(e.getMessages())
                        .setStatus(HttpStatus.BAD_REQUEST)
                        .setPath(
                                request.getRequestURI()
                                        .substring(
                                                request.getContextPath().length())
                        )
                );
    }
}
