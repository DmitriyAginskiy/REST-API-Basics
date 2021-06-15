package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {

    boolean insert(GiftCertificate certificate);

    boolean delete(long id);

    Optional<GiftCertificate> findById(long id);

    List<GiftCertificate> findAll();

    boolean updateCertificateTags(long id, List<Tag> tags);
}
