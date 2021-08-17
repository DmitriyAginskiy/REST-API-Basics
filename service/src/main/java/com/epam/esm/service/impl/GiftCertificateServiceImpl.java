package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.creator.FieldCondition;
import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoException;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.service.CertificateConditionStrategy;
import com.epam.esm.service.CriteriaStrategy;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.util.ExceptionMessageManager;
import com.epam.esm.util.constant.MessageKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import com.epam.esm.validator.GiftCertificateValidator;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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
    public GiftCertificate insert(GiftCertificate certificate) {
        if(certificate != null && GiftCertificateValidator.areValidFields(certificate)) {
            LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            certificate.setCreateDate(dateTime);
            certificate.setLastUpdateDate(dateTime);
            if(certificate.getTags() != null) {
                HashSet<Tag> tagsWithoutDuplicates = new HashSet<>(certificate.getTags());
                List<Tag> existingTags = tagService.findAllExisting(certificate.getTags());
                List<Tag> newTags = tagsWithoutDuplicates.stream().filter(t -> !existingTags
                        .contains(t)).collect(Collectors.toList());
                if(newTags.size() > 0) {
                    newTags.forEach(tagService::insert);
                }
                certificate.setTags(tagService.findAllExisting(certificate.getTags()));
            }
            try {
                long id = certificateDao.insert(certificate);
                Optional<GiftCertificate> certificateOptional = certificateDao.findById(id);
                return certificateOptional.orElseThrow(() -> new ElementSearchException(
                        ExceptionMessageManager.getMessage(MessageKey.ELEMENT_SEARCH_KEY, Locale.getDefault(), id)));
            } catch (DaoException e) {
                throw new ElementSearchException(ExceptionMessageManager.getMessage(MessageKey.ELEMENT_SEARCH_KEY, Locale.getDefault(), certificate.getId()));
            }
        } else {
            throw new InvalidFieldException(ExceptionMessageManager.getMessage(MessageKey.INVALID_FIELD_KEY, Locale.getDefault(), certificate.getId()));
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        Optional<GiftCertificate> giftCertificateOptional = certificateDao.findById(id);
        if(giftCertificateOptional.isPresent()) {
            GiftCertificate giftCertificate = giftCertificateOptional.get();
            if(giftCertificate.getTags() != null && !giftCertificate.getTags().isEmpty()) {
                certificateDao.removeTagsFromCertificate(id);
            }
            certificateDao.delete(id);
        } else {
            throw new ElementSearchException(ExceptionMessageManager.getMessage(MessageKey.ELEMENT_SEARCH_KEY, Locale.getDefault(), id));
        }
    }

    @Transactional
    @Override
    public GiftCertificate update(long id, GiftCertificate certificate) {
        System.out.println("update: " + TransactionSynchronizationManager.isActualTransactionActive());
        if(id == certificate.getId()) {
            Optional<GiftCertificate> giftCertificateOptional = certificateDao.findById(id);
            if(giftCertificateOptional.isPresent()) {
                List<FieldCondition> conditionList = CertificateConditionStrategy.createConditionsList(certificate);
                try {
                    certificateDao.update(id, conditionList);
                } catch (DaoException e) {
                    throw new ElementSearchException(ExceptionMessageManager.getMessage(MessageKey.ELEMENT_SEARCH_KEY, Locale.getDefault(), id));
                }
                if(GiftCertificateValidator.areTagsValid(certificate.getTags())) {
                    certificateDao.removeTagsFromCertificate(id);
                    HashSet<Tag> tagsWithoutDuplicates = new HashSet<>(certificate.getTags());
                    List<Tag> existingTags = tagService.findAllExisting(certificate.getTags());
                    List<Tag> newTags = tagsWithoutDuplicates.stream().filter(t -> !existingTags
                            .contains(t)).collect(Collectors.toList());
                    if(newTags.size() > 0) {
                        newTags.forEach(tagService::insert);
                    }
                    certificateDao.updateCertificateTags(id, tagService.findAllExisting(certificate.getTags()));
                }
                return certificateDao.findById(id).orElseThrow(() -> new ElementSearchException(
                        ExceptionMessageManager.getMessage(MessageKey.ELEMENT_SEARCH_KEY, Locale.getDefault(), id)));
            } else {
                throw new ElementSearchException(ExceptionMessageManager.getMessage(
                        MessageKey.ELEMENT_SEARCH_KEY, Locale.getDefault(), id));
            }
        } else {
            throw new InvalidFieldException("Certificate id mismatch - (" + id + ", " + certificate.getId() + ")");
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
    public List<GiftCertificate> findAll(String certificateName, String tagName, String description, String sortByDate, String sortByName) {
        List<Criteria> criteriaList = new ArrayList<>();
        String[] criteriaArray = new String[] { certificateName, tagName, description, sortByDate, sortByName };
        int counter = 0;
        for(CriteriaStrategy criteriaStrategy : CriteriaStrategy.values()) {
            Optional<Criteria> criteriaOptional = criteriaStrategy.createCriteria(criteriaArray[counter++]);
            criteriaOptional.ifPresent(criteriaList::add);
        }
        if(criteriaList.isEmpty()) {
            return certificateDao.findAll();
        } else {
            return certificateDao.findAllByCriteria(criteriaList);
        }
    }
}
