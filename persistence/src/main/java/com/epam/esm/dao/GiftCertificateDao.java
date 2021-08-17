package com.epam.esm.dao;

import com.epam.esm.dao.creator.FieldCondition;
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
     * @return long value with created object id.
     * @throws DaoException if object is not inserted
     */
    long insert(GiftCertificate certificate) throws DaoException;

    /**
     * Deletes gift certificate from the table.
     *
     * @param id of the object to be deleted.
     *
     */
    void delete(long id);

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
     * @param conditionList with fields to be updated.
     * @throws DaoException if resources does not exist
     */
    void update(long id, List<FieldCondition> conditionList) throws DaoException;

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
