package com.epam.esm.dao.constant;

public class TagQuery {

    public static final String FIND_ALL_TAGS = "SELECT * FROM tags;";

    public static final String FIND_TAGS_BY_CERTIFICATE = "SELECT * FROM tags INNER JOIN gift_certificates_has_tags WHERE"
            + " gift_certificates_id_fk = ?;";

    public static final String FIND_BY_NAME = "SELECT * FROM tags WHERE tag_name = ?;";

    public static final String INSERT_TAG = "INSERT INTO tags (tag_name) VALUES(?);";

    private TagQuery() {

    }
}
