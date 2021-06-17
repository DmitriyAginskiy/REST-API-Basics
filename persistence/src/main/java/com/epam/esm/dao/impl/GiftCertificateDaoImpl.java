package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.GiftCertificateQuery;
import com.epam.esm.dao.creator.GiftCertificateQueryCreator;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoException;
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

/**
 * GiftCertificateDao implementation.
 *
 * @author Dzmitry Ahinski
 */
@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateMapper mapper;

    @Autowired
    public GiftCertificateDaoImpl(DataSource dataSource, GiftCertificateMapper mapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.mapper = mapper;
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
            return true;
        } else {
            throw new DaoException("Element not added!");
        }
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(GiftCertificateQuery.DELETE_CERTIFICATE_QUERY, id) == 1;
    }

    @Override
    public boolean removeTagsFromCertificate(long id) {
        return jdbcTemplate.update(GiftCertificateQuery.REMOVE_TAGS_FROM_CERTIFICATE, id) > 0;
    }

    @Override
    public boolean update(long id, GiftCertificate certificate) {
        return jdbcTemplate.update(GiftCertificateQuery.UPDATE_CERTIFICATE_QUERY, certificate.getName(),
                certificate.getDescription(), certificate.getPrice(), certificate.getDuration(), certificate.getLastUpdateDate(),
                id) == 1;
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        return jdbcTemplate.query(GiftCertificateQuery.FIND_BY_ID_QUERY, mapper, id).stream().findFirst();
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(GiftCertificateQuery.FIND_ALL_QUERY, mapper);
    }

    @Override
    public List<GiftCertificate> findAllByCriteria(List<Criteria> criteriaList) {
        String query = GiftCertificateQueryCreator.createQuery(criteriaList);
        System.out.println(query);
        return jdbcTemplate.query(query, mapper);
    }

    @Override
    public boolean updateCertificateTags(long id, List<Tag> tags) {
        return tags.stream().allMatch(t -> jdbcTemplate.update(GiftCertificateQuery.CERTIFICATE_UPDATE_TAGS, id, t.getId()) == 1);
    }
}
