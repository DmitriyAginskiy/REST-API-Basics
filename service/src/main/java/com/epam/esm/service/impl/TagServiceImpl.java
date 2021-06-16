package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public boolean insert(Tag tag) {
        Optional<Tag> tagOptional = tagDao.findByName(tag.getName());
        if(!TagValidator.isNameValid(tag.getName()) || tagOptional.isPresent()) {
            return false;
        }
        return tagDao.insert(tag);
    }

    @Override
    public boolean delete(long id) {
        Optional<Tag> tagOptional = tagDao.findById(id);
        if(tagOptional.isPresent()) {
            return tagDao.delete(id);
        }
        return false;
    }

    @Override
    public Optional<Tag> findById(long id) {
        return tagDao.findById(id);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    public List<Tag> findTagsFromCertificate(long id) {
        return tagDao.findTagsFromCertificate(id);
    }

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }
}
