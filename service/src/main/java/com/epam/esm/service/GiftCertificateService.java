package com.epam.esm.service;

import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateService {

    boolean insert(GiftCertificate certificate);

    boolean delete(long id);

    boolean update(long id, GiftCertificate certificate);

    GiftCertificate findById(long id);

    List<GiftCertificate> findAllByCriteria(String certificateName, String tagName, String description,
                                            String sortByDate, String sortByName);

    List<GiftCertificate> findAll();
}
