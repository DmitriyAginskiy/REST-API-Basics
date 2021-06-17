package com.epam.esm.dao;

import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {

    boolean insert(GiftCertificate certificate);

    boolean delete(long id);

    boolean removeTagsFromCertificate(long id);

    boolean update(long id, GiftCertificate certificate);

    Optional<GiftCertificate> findById(long id);

    List<GiftCertificate> findAll();

    List<GiftCertificate> findAllByCriteria(List<Criteria> criteriaList);

    boolean updateCertificateTags(long id, List<Tag> tags);
}
