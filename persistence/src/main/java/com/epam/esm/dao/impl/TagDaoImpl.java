package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.TagQuery;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

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
    public boolean insert(Tag tag) {
        return jdbcTemplate.update(TagQuery.INSERT_TAG, tag.getName()) == 1;
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(TagQuery.DELETE_TAG_QUERY, id) == 1;
    }

    @Override
    public List<Tag> findTagsFromCertificate(long id) {
        return jdbcTemplate.query(TagQuery.FIND_TAGS_BY_CERTIFICATE, mapper, id);
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
}
