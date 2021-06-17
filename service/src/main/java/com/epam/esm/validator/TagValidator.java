package com.epam.esm.validator;

import java.util.regex.Pattern;

/**
 * Validates tag.
 *
 * @author Dzmitry Ahinski
 */
public class TagValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9-_\\.]{2,50}");

    public static boolean isNameValid(String name) {
        return (name != null) && NAME_PATTERN.matcher(name).matches();
    }
}
