package com.epam.esm.dao.constant;

/**
 * Class with the tags queries.
 *
 * @author Dzmitry Ahinski
 */
public final class TagQuery {

    public static final String FIND_ALL_TAGS = "SELECT * FROM tags;";

    public static final String FIND_TAGS_BY_CERTIFICATE = "SELECT * FROM tags INNER JOIN gift_certificates_has_tags WHERE"
            + " gift_certificates_id_fk = ?;";

    public static final String FIND_BY_NAME = "SELECT * FROM tags WHERE tag_name = ?;";

    public static final String INSERT_TAG = "INSERT INTO tags (tag_name) VALUES(?);";

    public static final String FIND_BY_ID_QUERY = "SELECT * FROM tags WHERE tag_id = ?;";

    public static final String DISCONNECT_TAG_FROM_CERTIFICATES = "DELETE FROM gift_certificates_has_tags WHERE tags_id_fk = ?;";

    public static final String DELETE_TAG_QUERY = "DELETE FROM tags WHERE tag_id = ?;";

    private TagQuery() {

    }
}
