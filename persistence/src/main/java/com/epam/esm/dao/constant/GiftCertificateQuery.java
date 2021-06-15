package com.epam.esm.dao.constant;

public class GiftCertificateQuery {

    public static final String INSERT_GIFT_CERTIFICATE_QUERY = "INSERT INTO gift_certificates (name, description,"
            + " price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?);";

    public static final String CERTIFICATE_UPDATE_TAGS = "INSERT INTO gift_certificates_has_tags (gift_certificates_id,"
            + " tags_id) VALUES (?, ?)";

    private GiftCertificateQuery() {

    }
}
