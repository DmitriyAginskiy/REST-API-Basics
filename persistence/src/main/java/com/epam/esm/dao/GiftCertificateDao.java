package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {

    void insert(GiftCertificate certificate);

    void delete(long id);

    Optional<GiftCertificate> findById(long id);

    List<GiftCertificate> findAll();
}
