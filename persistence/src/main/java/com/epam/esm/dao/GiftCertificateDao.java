package com.epam.esm.dao;

import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with gift certificates table.
 *
 * @author Dzmitry Ahinski
 */
public interface GiftCertificateDao {

    /**
     * Adds gift certificate to the table.
     *
     * @param certificate object to be added.
     * @throws DaoException if object is not inserted
     */
    void insert(GiftCertificate certificate) throws DaoException;

    /**
     * Deletes gift certificate from the table.
     *
     * @param id of the object to be deleted.
     * @return true if the object was deleted, otherwise - false.
     */
    boolean delete(long id);

    /**
     * Removes all tags from the certificate.
     *
     * @param id of the object to be updated.
     * @return true if the tags were removed, otherwise - false.
     */
    boolean removeTagsFromCertificate(long id);

    /**
     * Updates the certificate.
     *
     * @param id of the object to be updated.
     * @param certificate new object.
     * @return returns true if the object was updated, otherwise - false.
     */
    boolean update(long id, GiftCertificate certificate);

    /**
     * Finds the certificate by id.
     *
     * @param id of the object to be found.
     * @return optional object with found certificate.
     */
    Optional<GiftCertificate> findById(long id);

    /**
     * Finds all the certificates.
     *
     * @return list with found certificates
     */
    List<GiftCertificate> findAll();

    /**
     * Finds all the certificates by search and sort criteria.
     *
     * @param criteriaList list of criteria
     * @return list with found certificates.
     */
    List<GiftCertificate> findAllByCriteria(List<Criteria> criteriaList);

    /**
     * Updates certificate tags.
     *
     * @param id the id of object to be updated
     * @param tags the list of the tags to connect.
     * @return true if the tags were connected, otherwise - false.
     */
    boolean updateCertificateTags(long id, List<Tag> tags);
}
