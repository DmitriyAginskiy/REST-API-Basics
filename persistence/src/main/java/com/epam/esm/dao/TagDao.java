package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao {

    boolean insert(Tag tag);

    List<Tag> findTagsFromCertificate(long id);

    Optional<Tag> findByName(String name);

    List<Tag> findAll();
}
