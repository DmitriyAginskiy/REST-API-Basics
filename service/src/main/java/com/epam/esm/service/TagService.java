package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    boolean insert(Tag tag);

    Tag findByName(String name);

    List<Tag> findTagsFromCertificate(long id);

    List<Tag> findAll();
}
