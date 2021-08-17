package com.epam.esm.handler;

import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.handler.entity.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The ElementSearch exception handler.
 *
 * @author Dzmitry Ahinski
 */
@RestControllerAdvice
public class ElementSearchExceptionHandler {

    /**
     * Handles Element Search exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(ElementSearchException.class)
    public ResponseEntity<ExceptionResponse> handleException(ElementSearchException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), e.getLocalizedMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
