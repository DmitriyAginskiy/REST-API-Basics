package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
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
        return tagDao.insert(tag);
    }

    @Override
    public Tag findByName(String name) {
        return tagDao.findByName(name).get();
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
