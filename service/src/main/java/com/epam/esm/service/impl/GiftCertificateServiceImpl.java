package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoException;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.service.CertificateConditionStrategy;
import com.epam.esm.service.CriteriaStrategy;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import com.epam.esm.validator.GiftCertificateValidator;

/**
 * GiftCertificateService implementation.
 *
 * @author Dzmitry Ahinski
 */
@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao certificateDao;
    private final TagService tagService;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao certificateDao, TagService tagService) {
        this.certificateDao = certificateDao;
        this.tagService = tagService;
    }

    @Transactional
    @Override
    public void insert(GiftCertificate certificate) {
        if(certificate != null && GiftCertificateValidator.areValidFields(certificate)) {
            LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            certificate.setCreateDate(dateTime);
            certificate.setLastUpdateDate(dateTime);
            if(certificate.getTags() != null) {
                HashSet<Tag> tagsWithoutDuplicates = new HashSet<>(certificate.getTags());
                List<Tag> newTags = tagsWithoutDuplicates.stream().filter(t -> !tagService.findAll().contains(t)).collect(Collectors.toList());
                if(newTags.size() > 0) {
                    newTags.forEach(tagService::insert);
                }
                List<Tag> updatedTags = new ArrayList<>();
                tagsWithoutDuplicates.forEach(t -> updatedTags.add(tagService.findByName(t.getName())));
                certificate.setTags(updatedTags);
            }
        }
        try {
            certificateDao.insert(certificate);
        } catch (DaoException e) {
            throw new ElementSearchException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        Optional<GiftCertificate> giftCertificateOptional = certificateDao.findById(id);
        if(giftCertificateOptional.isPresent()) {
            GiftCertificate giftCertificate = giftCertificateOptional.get();
            if(giftCertificate.getTags() != null && !giftCertificate.getTags().isEmpty()) {
                certificateDao.removeTagsFromCertificate(id);
            }
            return certificateDao.delete(id);
        } else {
            throw new ElementSearchException("There is not element with id " + id);
        }
    }

    @Transactional
    @Override
    public GiftCertificate update(long id, GiftCertificate certificate) {
        Optional<GiftCertificate> giftCertificateOptional = certificateDao.findById(id);
        if(giftCertificateOptional.isPresent()) {
            GiftCertificate oldCertificate = giftCertificateOptional.get();
            updateCertificateFields(oldCertificate, certificate);
            oldCertificate.setLastUpdateDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
            List<Tag> oldTags = tagService.findTagsFromCertificate(id);
            List<Tag> newTags = tagService.findAll().stream()
                    .filter(t -> !oldTags.contains(t) && oldCertificate.getTags().contains(t))
                    .collect(Collectors.toList());
            certificateDao.updateCertificateTags(id, newTags);
            if(certificateDao.update(id, oldCertificate)) {
                return certificateDao.findById(id).orElseThrow(() -> new ElementSearchException("There is not element with id " + id));
            } else {
                throw new ElementSearchException("There is not element with id " + id);
            }
        } else {
            throw new ElementSearchException("There is not element with id " + id);
        }
    }

    @Override
    public GiftCertificate findById(long id) {
        Optional<GiftCertificate> certificate = certificateDao.findById(id);
        if(certificate.isPresent()) {
            return certificate.get();
        } else {
            throw new ElementSearchException("Element with id " + id + " is not founded!");
        }
    }

    @Override
    public List<GiftCertificate> findAllByCriteria(String certificateName, String tagName, String description, String sortByDate, String sortByName) {
        List<Criteria> criteriaList = new ArrayList<>();
        String[] criteriaArray = new String[] { certificateName, tagName, description, sortByDate, sortByName };
        int counter = 0;
        for(CriteriaStrategy criteriaStrategy : CriteriaStrategy.values()) {
            Optional<Criteria> criteriaOptional = criteriaStrategy.createCriteria(criteriaArray[counter++]);
            criteriaOptional.ifPresent(criteriaList::add);
        }
        return certificateDao.findAllByCriteria(criteriaList);
    }

    @Override
    public List<GiftCertificate> findAll() {
        return certificateDao.findAll();
    }

    private void updateCertificateFields(GiftCertificate oldCertificate, GiftCertificate newCertificate) {
        for(CertificateConditionStrategy strategy : CertificateConditionStrategy.values()) {
            strategy.changeCondition(oldCertificate, newCertificate);
        }
    }
}
