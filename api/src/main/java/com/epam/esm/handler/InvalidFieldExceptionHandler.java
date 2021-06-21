package com.epam.esm.handler;

import com.epam.esm.exception.InvalidFieldException;
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
    public ResponseEntity<String> handleException(InvalidFieldException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}