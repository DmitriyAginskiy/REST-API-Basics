package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.epam.esm.validator.GiftCertificateValidator;

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
                List<Tag> newTags = certificate.getTags().stream().filter(t -> !tagService.findAll().contains(t)).collect(Collectors.toList());
                if(newTags.size() > 0) {
                    newTags.forEach(tagService::insert);
                }
                List<Tag> updatedTags = new ArrayList<>();
                certificate.getTags().forEach(t -> updatedTags.add(tagService.findByName(t.getName())));
                certificate.setTags(updatedTags);
            }
            return certificateDao.insert(certificate);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        Optional<GiftCertificate> giftCertificateOptional = certificateDao.findById(id);
        if(giftCertificateOptional.isPresent()) {
            GiftCertificate giftCertificate = giftCertificateOptional.get();
            if(giftCertificate.getTags() != null && !giftCertificate.getTags().isEmpty()) {
                certificateDao.removeTagsFromCertificate(id);
            }
        }
        return certificateDao.delete(id);
    }

    @Transactional
    @Override
    public boolean update(long id, GiftCertificate certificate) {
        Optional<GiftCertificate> giftCertificateOptional = certificateDao.findById(id);
        boolean isUpdated = false;
        if(giftCertificateOptional.isPresent()) {
            GiftCertificate oldCertificate = giftCertificateOptional.get();
            updateCertificateFields(oldCertificate, certificate);
            oldCertificate.setLastUpdateDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
            List<Tag> oldTags = tagService.findTagsFromCertificate(id);
            List<Tag> newTags = tagService.findAll().stream()
                    .filter(t -> !oldTags.contains(t) && oldCertificate.getTags().contains(t))
                    .collect(Collectors.toList());
            certificateDao.updateCertificateTags(id, newTags);
            isUpdated = certificateDao.update(id, oldCertificate);
        }
        return isUpdated;
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        return certificateDao.findById(id);
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
