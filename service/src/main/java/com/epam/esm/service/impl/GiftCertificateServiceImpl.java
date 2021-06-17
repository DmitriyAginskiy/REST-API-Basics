package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.GiftCertificateColumnName;
import com.epam.esm.dao.constant.TagColumnName;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.creator.criteria.impl.SearchCriteria;
import com.epam.esm.dao.creator.criteria.impl.SortCriteria;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ElementNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
    public boolean insert(GiftCertificate certificate) {
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
        return certificateDao.insert(certificate);
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
            throw new ElementNotFoundException("There is not element with id " + id);
        }
    }

    @Transactional
    @Override
    public boolean update(long id, GiftCertificate certificate) {
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
            return certificateDao.update(id, oldCertificate);
        } else {
            throw new ElementNotFoundException("There is not element with id " + id);
        }
    }

    @Override
    public GiftCertificate findById(long id) {
        Optional<GiftCertificate> certificate = certificateDao.findById(id);
        if(certificate.isPresent()) {
            return certificate.get();
        } else {
            throw new ElementNotFoundException("Element with id " + id + " is not founded!");
        }
    }

    @Override
    public List<GiftCertificate> findAllByCriteria(String certificateName, String tagName, String description, String sortByDate, String sortByName) {
        List<Criteria> criteriaList = new ArrayList<>();
        if(GiftCertificateValidator.isNameValid(certificateName)) {
            criteriaList.add(new SearchCriteria(GiftCertificateColumnName.NAME, certificateName));
        }
        if(TagValidator.isNameValid(tagName)) {
            criteriaList.add(new SearchCriteria(TagColumnName.TAG_NAME, tagName));
        }
        if(GiftCertificateValidator.isDescriptionValid(description)) {
            criteriaList.add(new SearchCriteria(GiftCertificateColumnName.DESCRIPTION, description));
        }
        if(sortByDate != null && (sortByDate.equalsIgnoreCase(SortCriteria.SORT_ASC) || sortByDate.equalsIgnoreCase(SortCriteria.SORT_DESC))) {
            String sortType = sortByDate.equalsIgnoreCase(SortCriteria.SORT_ASC) ? SortCriteria.SORT_ASC : SortCriteria.SORT_DESC;
            criteriaList.add(new SortCriteria(GiftCertificateColumnName.CREATE_DATE, sortType));
        }
        if(sortByName != null && (sortByName.equalsIgnoreCase(SortCriteria.SORT_ASC) || sortByName.equalsIgnoreCase(SortCriteria.SORT_DESC))) {
            String sortType = sortByName.equalsIgnoreCase(SortCriteria.SORT_ASC) ? SortCriteria.SORT_ASC : SortCriteria.SORT_DESC;
            criteriaList.add(new SortCriteria(GiftCertificateColumnName.NAME, sortType));
        }
        return certificateDao.findAllByCriteria(criteriaList);
    }

    @Override
    public List<GiftCertificate> findAll() {
        return certificateDao.findAll();
    }

    private void updateCertificateFields(GiftCertificate oldCertificate, GiftCertificate newCertificate) {
        if(GiftCertificateValidator.isNameValid(newCertificate.getName())) {
            oldCertificate.setName(newCertificate.getName());
        }
        if(GiftCertificateValidator.isDescriptionValid(newCertificate.getDescription())) {
            oldCertificate.setDescription(newCertificate.getDescription());
        }
        if(GiftCertificateValidator.isPriceValid(newCertificate.getPrice())) {
            oldCertificate.setPrice(newCertificate.getPrice());
        }
        if(GiftCertificateValidator.isDurationValid(newCertificate.getDuration())) {
            oldCertificate.setDuration(newCertificate.getDuration());
        }
        if(GiftCertificateValidator.areTagsValid(newCertificate.getTags())) {
            oldCertificate.setTags(newCertificate.getTags());
        }
    }
}
