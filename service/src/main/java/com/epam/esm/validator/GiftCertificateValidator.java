package com.epam.esm.validator;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Validates gift certificate.
 *
 * @author Dzmitry Ahinski
 */
public class GiftCertificateValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z\\w\\s]{1,128}");

    public static boolean areValidFields(GiftCertificate certificate) {
        return isNameValid(certificate.getName()) && isDescriptionValid(certificate.getDescription())
                && isPriceValid(certificate.getPrice()) && isDurationValid(certificate.getDuration());
    }

    public static boolean isNameValid(String name) {
        return (name != null) && NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isDescriptionValid(String description) {
        return description != null && !description.isEmpty() && description.length() < 1000;
    }

    public static boolean isPriceValid(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean isDurationValid(int duration) {
        return duration > 0;
    }

    public static boolean areTagsValid(List<Tag> tags) {
        return tags.stream().allMatch(t -> TagValidator.isNameValid(t.getName()));
    }
}
