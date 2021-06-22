package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import java.util.List;

/**
 * Interface provides actions on tags.
 *
 * @author Dzmitry Ahinski
 */
public interface TagService {

    /**
     * Adds new tag.
     *
     * @param tag an object to be added.
     *
     */
    Tag insert(Tag tag);

    /**
     * Deletes tag.
     *
     * @param id of the object to be deleted.
     * @return true if the object was deleted, otherwise - false.
     */
    void delete(long id);

    /**
     * Finds tag by id.
     *
     * @param id of the object to be found.
     * @return found tag object
     */
    Tag findById(long id);

    /**
     * Finds all tags connected with the certificate.
     *
     * @param id of the certificate.
     * @return list of the found tags.
     */
    List<Tag> findTagsFromCertificate(long id);

    /**
     * Finds all tags.
     *
     * @return list of the found tags.
     */
    List<Tag> findAll();

    List<Tag> findAllExisting(List<Tag> tags);
}
