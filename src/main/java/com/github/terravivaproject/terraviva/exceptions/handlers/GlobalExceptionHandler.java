package com.github.terravivaproject.terraviva.exceptions.handlers;

import com.github.terravivaproject.terraviva.exceptions.ConfirmationJustExpiredException;
import com.github.terravivaproject.terraviva.exceptions.EntityDoesNotExist;
import com.github.terravivaproject.terraviva.exceptions.UserAlreadyExistsException;
import com.github.terravivaproject.terraviva.exceptions.model.ErrorDto;
import com.github.terravivaproject.terraviva.exceptions.model.MultipleErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<MultipleErrorDto> yourExceptionHandler(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getField() + " -> " + fieldError.getDefaultMessage());
        }

        return ResponseEntity
                .badRequest()
                .body(
                        new MultipleErrorDto()
                                .setStatus(HttpStatus.BAD_REQUEST)
                                .setError("There are error in the arguments of the request.")
                                .setErrorMessages(errors)
                                .setPath(
                                        request.getRequestURI()
                                                .substring(
                                                        request.getContextPath().length())
                                )
                );
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<MultipleErrorDto> userAlreadyExistsExceptionHandler(
            UserAlreadyExistsException e,
            HttpServletRequest request) {
        return ResponseEntity
                .badRequest()
                .body(new MultipleErrorDto()
                        .setError("This user already exists.")
                        .setErrorMessages(e.getMessages())
                        .setStatus(HttpStatus.BAD_REQUEST)
                        .setPath(
                                request.getRequestURI()
                                        .substring(
                                                request.getContextPath().length())
                        )
                );
    }

    @ExceptionHandler({EntityDoesNotExist.class})
    public ResponseEntity<ErrorDto> entityDoesNotExistExceptionHandler(
            EntityDoesNotExist e,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorDto()
                        .setError("The searched resource doesn't exist.")
                        .setErrorMessage(e.getMessage())
                        .setStatus(HttpStatus.NOT_FOUND)
                        .setPath(
                                request.getRequestURI()
                                        .substring(
                                                request.getContextPath().length())),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({ConfirmationJustExpiredException.class})
    public ResponseEntity<ErrorDto> confirmationJustExpiredExceptionHandler(
            ConfirmationJustExpiredException e,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorDto()
                        .setError("Too Late...")
                        .setErrorMessage(e.getMessage())
                        .setStatus(HttpStatus.NOT_FOUND)
                        .setPath(
                                request.getRequestURI()
                                        .substring(
                                                request.getContextPath().length())),
                HttpStatus.NOT_ACCEPTABLE
        );
    }
}
