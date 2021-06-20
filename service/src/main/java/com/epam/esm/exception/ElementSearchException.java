package com.epam.esm.exception;

/**
 * Custom ElementNotFound exception.
 *
 * @author Dzmitry Ahinski
 */
public class ElementSearchException extends RuntimeException {

    public ElementSearchException(String message) {
        super(message);
    }
}
