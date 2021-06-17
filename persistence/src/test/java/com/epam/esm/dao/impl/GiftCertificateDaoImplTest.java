package com.epam.esm.dao.impl;

import com.epam.esm.configuration.DatabaseConfiguration;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.GiftCertificateColumnName;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.creator.criteria.impl.SearchCriteria;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GiftCertificateDaoImplTest {

    private static final GiftCertificateDao dao = new GiftCertificateDaoImpl(new DatabaseConfiguration().embeddedDataSource(),
            new GiftCertificateMapper());

    @Test
    void delete() {
        boolean actual = dao.delete(3);
        assertTrue(actual);
    }

    @Test
    void update() {
        boolean actual = dao.update(522, new GiftCertificate());
        assertFalse(actual);
    }

    @Test
    void findById() {
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(3, "Black widow",
                "Marvel", new BigDecimal("100"), 125, LocalDateTime.of(2019, 9, 11, 15, 32, 12),
                LocalDateTime.of(2021, 3, 11, 15, 32, 12), new ArrayList<>()));
        Optional<GiftCertificate> actual = dao.findById(3);
        assertEquals(expected, actual);
    }

    @Test
    void findAllByCriteria() {
        GiftCertificate expected = new GiftCertificate(3, "Black widow", "Marvel",
                new BigDecimal("100"), 125, LocalDateTime.of(2019, 9, 11, 15, 32, 12),
                LocalDateTime.of(2021, 3, 11, 15, 32, 12), new ArrayList<>());
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(new SearchCriteria(GiftCertificateColumnName.DESCRIPTION, "Mar"));
        List<GiftCertificate> actual = dao.findAllByCriteria(criteriaList);
        assertEquals(expected, actual.get(0));
    }

    @Test
    void insert() {
        GiftCertificate certificate = new GiftCertificate(5, "Black widow2",  "Marvel2",
                new BigDecimal("10"), 15, LocalDateTime.of(2019, 9, 11, 15, 32, 12),
                LocalDateTime.of(2021, 3, 11, 15, 32, 12), new ArrayList<>());
        boolean actual = dao.insert(certificate);
        assertTrue(actual);
    }
}