package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.GiftCertificateQuery;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean insert(GiftCertificate certificate) {
        boolean isInserted = jdbcTemplate.update(GiftCertificateQuery.INSERT_GIFT_CERTIFICATE_QUERY, certificate.getName(),
                certificate.getDescription(), certificate.getPrice(), certificate.getDuration(), certificate.getCreateDate(),
                certificate.getLastUpdateDate()) == 1;
        return isInserted;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<GiftCertificate> findAll() {
        return null;
    }

    @Override
    public boolean updateCertificateTags(long id, List<Tag> tags) {
        return tags.stream().allMatch(t -> jdbcTemplate.update(GiftCertificateQuery.CERTIFICATE_UPDATE_TAGS) == 1);
    }
}
