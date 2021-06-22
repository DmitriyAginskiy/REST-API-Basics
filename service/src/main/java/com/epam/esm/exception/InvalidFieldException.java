package com.epam.esm.exception;

/**
 * Custom InvalidField exception.
 *
 * @author Dzmitry Ahinski
 */
public class InvalidFieldException extends RuntimeException {

    public InvalidFieldException(String message) {
        super(message);
    }
}
