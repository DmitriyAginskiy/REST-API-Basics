package com.epam.esm.util;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class ExceptionMessageManager {

    private static final String MESSAGE_PATH = "locale.message";

    public static String getMessage(String messageKey, Locale locale, long id) {
        return new String(ResourceBundle
                .getBundle(MESSAGE_PATH, locale)
                .getString(messageKey)
                .getBytes(StandardCharsets.ISO_8859_1)) + id;
    }
}
