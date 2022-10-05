package com.github.terravivaproject.terraviva.exceptions.handlers;

import com.github.terravivaproject.terraviva.exceptions.model.MultipleErrorDto;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Handle Method Argument Not Valid Exception
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
@ControllerAdvice
public class MethodArgumentNotValid_EXC_HAN {
    /**
     * methodArgumentNotValidExceptionHandler.
     *
     * @param e       a {@link org.springframework.web.bind.MethodArgumentNotValidException} object
     * @param request a {@link javax.servlet.http.HttpServletRequest} object
     * @return a {@link org.springframework.http.ResponseEntity} object
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "the server cannot validate the arguments of the request")
    public ResponseEntity<MultipleErrorDto> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {

        //Get all the error fields form the exception
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        /*
         * Map the list of object with attributes ("field", "message")
         * to a map with key "field" and value a list of "message"
         */
        Map<String, List<String>> errors = fieldErrors
                .stream()
                .collect(
                        Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(
                                        FieldError::getDefaultMessage,
                                        Collectors.toList()
                                )
                        )
                );

        return ResponseEntity
                .badRequest()
                .body(
                        new MultipleErrorDto()
                                .setStatus(HttpStatus.BAD_REQUEST)
                                .setError(ErrorMessagesService.requestError())
                                .setErrorMessages(errors)
                                .setPath(
                                        request.getRequestURI()
                                                .substring(
                                                        request.getContextPath().length())
                                )
                );
    }
}
