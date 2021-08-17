package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.TagQuery;
import com.epam.esm.dao.creator.TagQueryCreator;
import com.epam.esm.dao.mapper.TagMapper;
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
import java.util.List;
import java.util.Optional;

/**
 * TagDao implementation.
 *
 * @author Dzmitry Ahinski
 */
@Repository
public class TagDaoImpl implements TagDao {
    private final JdbcTemplate jdbcTemplate;
    private final TagMapper mapper;

    @Autowired
    public TagDaoImpl(DataSource source, TagMapper mapper) {
        this.jdbcTemplate = new JdbcTemplate(source);
        this.mapper = mapper;
    }

    @Override
    public long insert(Tag tag) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean isInserted = jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(TagQuery.INSERT_TAG, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tag.getName());
            return statement;
        }, keyHolder) == 1;
        if(isInserted && keyHolder.getKey() != null) {
            return keyHolder.getKey().longValue();
        } else {
            throw new DaoException("Element " + tag + " is not added!");
        }
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(TagQuery.DELETE_TAG_QUERY, id);
    }

    @Override
    public Optional<Tag> findById(long id) {
        return jdbcTemplate.query(TagQuery.FIND_BY_ID_QUERY, mapper, id).stream().findFirst();
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return jdbcTemplate.query(TagQuery.FIND_BY_NAME, mapper, name).stream().findFirst();
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(TagQuery.FIND_ALL_TAGS, mapper);
    }

    @Override
    public List<Tag> findAllExisting(List<Tag> tags) {
        String query = TagQueryCreator.createExistingTagsSelectionQuery(tags.size());
        return jdbcTemplate.query(con -> {
            PreparedStatement statement = con.prepareStatement(query);
            for(int i = 0; i < tags.size(); i++) {
                statement.setString(i + 1, tags.get(i).getName());
            }
            return statement;
        }, mapper);
    }

    @Override
    public void disconnectTagFromCertificates(long id) {
        jdbcTemplate.update(TagQuery.DISCONNECT_TAG_FROM_CERTIFICATES, id);
    }
}
