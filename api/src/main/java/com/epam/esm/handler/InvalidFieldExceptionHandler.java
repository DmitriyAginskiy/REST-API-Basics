package com.epam.esm.handler;

import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.handler.entity.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The InvalidField exception handler.
 *
 * @author Dzmitry Ahinski
 */
@RestControllerAdvice
public class InvalidFieldExceptionHandler {

    /**
     * Handles Invalid Field exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ExceptionResponse> handleException(InvalidFieldException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), e.getLocalizedMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}