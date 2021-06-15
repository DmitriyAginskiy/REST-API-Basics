package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.GiftCertificateQuery;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean insert(GiftCertificate certificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean isInserted = jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(GiftCertificateQuery.INSERT_GIFT_CERTIFICATE_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, certificate.getName());
            statement.setString(2, certificate.getDescription());
            statement.setBigDecimal(3, certificate.getPrice());
            statement.setInt(4, certificate.getDuration());
            statement.setTimestamp(5, Timestamp.valueOf(certificate.getCreateDate()));
            statement.setTimestamp(6, Timestamp.valueOf(certificate.getLastUpdateDate()));
            return statement;
        }, keyHolder) == 1;
        if (isInserted && keyHolder.getKey() != null) {
            if (certificate.getTags() != null && !certificate.getTags().isEmpty()) {
                updateCertificateTags(keyHolder.getKey().longValue(), certificate.getTags());
            }
        }
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
        return tags.stream().allMatch(t -> jdbcTemplate.update(GiftCertificateQuery.CERTIFICATE_UPDATE_TAGS, id, t.getId()) == 1);
    }
}
