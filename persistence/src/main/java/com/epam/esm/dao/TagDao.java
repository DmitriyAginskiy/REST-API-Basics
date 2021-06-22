package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with tags table.
 *
 * @author Dzmitry Ahinski
 */
public interface TagDao {

    /**
     * Adds tag to the table.
     *
     * @param tag object to be added.
     * @return long value with id of added object.
     * @throws DaoException if object was not added.
     */
    long insert(Tag tag) throws DaoException;

    /**
     * Deletes tag from the table.
     *
     * @param id of the object to be deleted.
     *
     */
    void delete(long id);

    /**
     * Finds tags by certificate id.
     *
     * @param id of the connected certificate.
     * @return list of found tags.
     */
    List<Tag> findTagsFromCertificate(long id);

    /**
     * Finds the tag by id.
     *
     * @param id of the object to be found.
     * @return optional object with found tag.
     */
    Optional<Tag> findById(long id);

    /**
     * Finds the tag by name.
     *
     * @param name of the object to be found.
     * @return optional object with found tag.
     */
    Optional<Tag> findByName(String name);

    /**
     * Finds all the tags.
     *
     * @return list with found tags
     */
    List<Tag> findAll();

    /**
     * Finds all the existing tags.
     *
     * @return list with found tags
     */
    List<Tag> findAllExisting(List<Tag> tags);

    /**
     * Disconnects tag from all certificates.
     *
     * @param id of the tag
     */
    void disconnectTagFromCertificates(long id);
}
