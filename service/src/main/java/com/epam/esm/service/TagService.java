package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    boolean insert(Tag tag);

    boolean delete(long id);

    Optional<Tag> findById(long id);

    Optional<Tag> findByName(String name);

    List<Tag> findTagsFromCertificate(long id);

    List<Tag> findAll();
}
