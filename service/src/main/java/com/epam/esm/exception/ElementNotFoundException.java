package com.epam.esm.exception;

/**
 * Custom ElementNotFound exception.
 *
 * @author Dzmitry Ahinski
 */
public class ElementNotFoundException extends RuntimeException {

    public ElementNotFoundException(String message) {
        super(message);
    }
}
