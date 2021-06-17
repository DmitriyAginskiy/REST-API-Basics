package com.epam.esm.handler;

import com.epam.esm.exception.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ElementNotFoundExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<String> handleElementNotFoundException(ElementNotFoundException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
