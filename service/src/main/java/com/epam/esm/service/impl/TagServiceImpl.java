package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoException;
import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * TagService implementation.
 *
 * @author Dzmitry Ahinski
 */
@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public void insert(Tag tag) {
        Optional<Tag> tagOptional = tagDao.findByName(tag.getName());
        if(!TagValidator.isNameValid(tag.getName()) || tagOptional.isEmpty()) {
            try {
                tagDao.insert(tag);
            } catch (DaoException e) {
                throw new ElementSearchException(e.getMessage());
            }
        } else {
            throw new InvalidFieldException("Invalid name field");
        }
    }

    @Override
    public boolean delete(long id) {
        Optional<Tag> tagOptional = tagDao.findById(id);
        if(tagOptional.isPresent()) {
            return tagDao.delete(id);
        } else {
            throw new ElementSearchException("There is not element with id " + id);
        }
    }

    @Override
    public Tag findById(long id) {
        Optional<Tag> tagOptional = tagDao.findById(id);
        if(tagOptional.isPresent()) {
            return tagOptional.get();
        } else {
            throw new ElementSearchException("There is not element with id " + id);
        }
    }

    @Override
    public Tag findByName(String name) {
        Optional<Tag> tagOptional = tagDao.findByName(name);
        if(tagOptional.isPresent()) {
            return tagOptional.get();
        } else {
            throw new ElementSearchException("There is not element with name " + name);
        }
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
