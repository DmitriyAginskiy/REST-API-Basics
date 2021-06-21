package com.epam.esm.dao.constant;

/**
 * Class with the gift certificates queries.
 *
 * @author Dzmitry Ahinski
 */
public class GiftCertificateQuery {

    public static final String INSERT_GIFT_CERTIFICATE_QUERY = "INSERT INTO gift_certificates (certificate_name, description,"
            + " price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?);";

    public static final String CERTIFICATE_UPDATE_TAGS = "INSERT INTO gift_certificates_has_tags (gift_certificates_id_fk,"
            + " tags_id_fk) VALUES (?, ?);";

    public static final String DELETE_CERTIFICATE_QUERY = "DELETE FROM gift_certificates WHERE certificate_id = ?;";

    public static final String REMOVE_TAGS_FROM_CERTIFICATE = "DELETE FROM gift_certificates_has_tags WHERE gift_certificates_id_fk = ?;";

    public static final String FIND_ALL_QUERY = "SELECT certificate_id, certificate_name, description, price, duration, create_date, last_update_date, tag_id, tag_name FROM gift_certificates LEFT JOIN " +
            "gift_certificates_has_tags ON certificate_id = gift_certificates_id_fk LEFT JOIN tags ON tag_id = tags_id_fk;";

    public static final String FIND_BY_ID_QUERY = "SELECT gift_certificates.*, tags.* FROM gift_certificates LEFT JOIN gift_certificates_has_tags ON" +
            " certificate_id = gift_certificates_id_fk LEFT JOIN tags ON tag_id = tags_id_fk WHERE certificate_id = ?;";

    public static final String UPDATE_CERTIFICATE_QUERY = "UPDATE gift_certificates SET certificate_name = ?," +
            " description = ?, price = ?, duration = ?, last_update_date = ? WHERE certificate_id = ?;";

    private GiftCertificateQuery() {

    }
}
