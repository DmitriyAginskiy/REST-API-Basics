package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.TagQuery;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class TagDaoImpl implements TagDao {
    private final JdbcTemplate jdbcTemplate;
    private final TagMapper mapper;

    @Autowired
    public TagDaoImpl(DataSource source, TagMapper mapper) {
        this.jdbcTemplate = new JdbcTemplate(source);
        this.mapper = mapper;
    }
    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(TagQuery.FIND_ALL_TAGS, mapper);
    }
}
