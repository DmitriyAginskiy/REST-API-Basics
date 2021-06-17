package com.epam.esm.handler;

import com.epam.esm.exception.ElementNotFoundException;
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
public class ElementNotFoundExceptionHandler {

    /**
     * Handles ElementNotFound exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<String> handleElementNotFoundException(ElementNotFoundException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
