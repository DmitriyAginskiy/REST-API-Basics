package com.epam.esm.dao.mapper;

import com.epam.esm.dao.constant.GiftCertificateColumnName;
import com.epam.esm.dao.constant.TagColumnName;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Gift certificates mapper.
 *
 * @author Dzmitry Ahinski
 */
@Component
public class GiftCertificateMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        long certificateId = rs.getLong(GiftCertificateColumnName.ID);
        String certificateName = rs.getString(GiftCertificateColumnName.NAME);
        String description = rs.getString(GiftCertificateColumnName.DESCRIPTION);
        BigDecimal price = rs.getBigDecimal(GiftCertificateColumnName.PRICE);
        int duration = rs.getInt(GiftCertificateColumnName.DURATION);
        LocalDateTime createDate = rs.getObject(GiftCertificateColumnName.CREATE_DATE, LocalDateTime.class);
        LocalDateTime lastUpdateDate = rs.getObject(GiftCertificateColumnName.LAST_UPDATE_DATE, LocalDateTime.class);
        List<Tag> tags = new ArrayList<>();
        while(!rs.isAfterLast() && rs.getLong(GiftCertificateColumnName.ID) == certificateId) {
            long tagId = rs.getLong(TagColumnName.TAG_ID);
            String name = rs.getString(TagColumnName.TAG_NAME);
            if(tagId != 0 && name != null) {
                tags.add(new Tag(tagId, name));
            }
            rs.next();
        }
        return new GiftCertificate(certificateId, certificateName, description, price, duration, createDate, lastUpdateDate, tags);
    }
}
