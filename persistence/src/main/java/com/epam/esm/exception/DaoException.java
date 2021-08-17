package com.epam.esm.exception;

import org.springframework.context.support.MessageSourceResourceBundle;

import java.util.ResourceBundle;

/**
 * Custom Dao exception.
 *
 * @author Dzmitry Ahinski
 */
public class DaoException extends Exception {

    public DaoException(String message) {
        super(message);
    }

}
