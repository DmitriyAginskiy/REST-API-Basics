package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateService {

    boolean insert(GiftCertificate certificate);

    boolean delete(long id);

    boolean update(long id, GiftCertificate certificate);

    Optional<GiftCertificate> findById(long id);

    List<GiftCertificate> findAll();
}
