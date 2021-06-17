package com.epam.esm.handler;

import com.epam.esm.exception.DaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DaoExceptionHandler {

    @ExceptionHandler(DaoException.class)
    public ResponseEntity<String> handleDaoException(DaoException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
