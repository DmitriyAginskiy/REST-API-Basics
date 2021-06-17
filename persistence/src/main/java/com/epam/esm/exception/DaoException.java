package com.epam.esm.exception;

/**
 * Custom Dao exception.
 *
 * @author Dzmitry Ahinski
 */
public class DaoException extends RuntimeException {

    public DaoException(String message) {
        super(message);
    }
}
