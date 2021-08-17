package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.GiftCertificateColumnName;
import com.epam.esm.dao.constant.GiftCertificateQuery;
import com.epam.esm.dao.creator.FieldCondition;
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
import java.math.BigDecimal;
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
    public long insert(GiftCertificate certificate) throws DaoException {
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
            return keyHolder.getKey().longValue();
        } else {
            throw new DaoException("Element with id " + certificate.getId() + " was not inserted!");
        }
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(GiftCertificateQuery.DELETE_CERTIFICATE_QUERY, id);
    }

    @Override
    public boolean removeTagsFromCertificate(long id) {
        return jdbcTemplate.update(GiftCertificateQuery.REMOVE_TAGS_FROM_CERTIFICATE, id) > 0;
    }

    @Override
    public void update(long id, List<FieldCondition> conditionList) throws DaoException {
        String query = GiftCertificateQueryCreator.createUpdateQuery(conditionList);
        boolean isUpdated = jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            FieldCondition buffer = null;
            for(int i = 1; i < conditionList.size() + 1; i++) {
                buffer = conditionList.get(i - 1);
                switch (buffer.getName()) {
                    case GiftCertificateColumnName.PRICE: {
                        statement.setBigDecimal(i, new BigDecimal(buffer.getValue()));
                        break;
                    } case GiftCertificateColumnName.DURATION: {
                        statement.setInt(i, Integer.parseInt(buffer.getValue()));
                        break;
                    } default: {
                        statement.setString(i, buffer.getValue());
                        break;
                    }
                }
            }
            statement.setLong(conditionList.size() + 1, id);
            return statement;
        }) == 1;
        if (!isUpdated) {
            throw new DaoException("Element with id " + id + " was not updated!");
        }
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
        String query = GiftCertificateQueryCreator.createSearchQuery(criteriaList);
        return jdbcTemplate.query(query, mapper);
    }

    @Override
    public boolean updateCertificateTags(long id, List<Tag> tags) {
        return tags.stream().allMatch(tag -> jdbcTemplate.update(GiftCertificateQuery.CERTIFICATE_UPDATE_TAGS, id, tag.getId()) == 1);
    }
}
