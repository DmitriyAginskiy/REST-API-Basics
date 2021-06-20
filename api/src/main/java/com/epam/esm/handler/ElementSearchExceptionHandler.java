package com.epam.esm.handler;

import com.epam.esm.exception.ElementSearchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The ElementNotFound exception handler.
 *
 * @author Dzmitry Ahinski
 */
@RestControllerAdvice
public class ElementSearchExceptionHandler {

    /**
     * Handles all exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
