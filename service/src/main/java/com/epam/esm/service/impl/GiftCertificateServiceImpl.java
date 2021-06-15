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
import java.util.stream.Collectors;

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
        LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        certificate.setCreateDate(dateTime);
        certificate.setLastUpdateDate(dateTime);
        if(certificate.getTags() != null) {
            List<Tag> newTags = certificate.getTags().stream().filter(t -> !tagService.findAll().contains(t)).collect(Collectors.toList());
            System.out.println("New tags: " + newTags.size());
            if(newTags.size() > 0) {
                newTags.forEach(tagService::insert);
            }
            List<Tag> updatedTags = new ArrayList<>();
            certificate.getTags().forEach(t -> updatedTags.add(tagService.findByName(t.getName())));
            certificate.setTags(updatedTags);
        }
        return certificateDao.insert(certificate);
    }
}
