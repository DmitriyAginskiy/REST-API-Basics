package com.epam.esm.validator;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GiftCertificateValidatorTest {

    private static GiftCertificate certificate;

    @BeforeAll
    public static void setUp() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "TagName"));
        certificate = new GiftCertificate(1, "SomeName", "Description", new BigDecimal("15.2"), 5, LocalDateTime.now(),
                LocalDateTime.now(), tags);
    }

    @Test
    void areValidFields() {
        boolean actual = GiftCertificateValidator.areValidFields(certificate);
        assertTrue(actual);
    }

    @Test
    void isNameValid() {
        boolean actual = GiftCertificateValidator.isNameValid(certificate.getName());
        assertTrue(actual);
    }

    @Test
    void isDescriptionValid() {
        boolean actual = GiftCertificateValidator.isDescriptionValid(certificate.getDescription());
        assertTrue(actual);
    }

    @Test
    void isPriceValid() {
        boolean actual = GiftCertificateValidator.isPriceValid(certificate.getPrice());
        assertTrue(actual);
    }

    @Test
    void isDurationValid() {
        boolean actual = GiftCertificateValidator.isDurationValid(certificate.getDuration());
        assertTrue(actual);
    }

    @Test
    void areTagsValid() {
        boolean actual = GiftCertificateValidator.areTagsValid(certificate.getTags());
        assertTrue(actual);
    }
}