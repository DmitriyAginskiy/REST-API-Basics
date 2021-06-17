package com.epam.esm.dao.mapper;

import com.epam.esm.dao.constant.TagColumnName;
import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Tags mapper.
 *
 * @author Dzmitry Ahinski
 */
@Component
public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong(TagColumnName.TAG_ID);
        String name = rs.getString(TagColumnName.TAG_NAME);
        return new Tag(id, name);
    }
}
