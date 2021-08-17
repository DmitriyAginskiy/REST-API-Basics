package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import java.util.List;

/**
 * Interface provides actions on gift certificates.
 *
 * @author Dzmitry Ahinski
 */
public interface GiftCertificateService {

    /**
     * Adds new gift certificate.
     *
     * @param certificate an object to be added.
     * @return added GiftCertificate object
     */
    GiftCertificate insert(GiftCertificate certificate);

    /**
     * Deletes gift certificate.
     *
     * @param id of the object to be deleted.
     *
     */
   void delete(long id);

    /**
     * Updates gift certificate.
     *
     * @param id of the object to be updated.
     * @param certificate with new fields.
     * @return updated object.
     */
    GiftCertificate update(long id, GiftCertificate certificate);

    /**
     * Finds gift certificate by id.
     *
     * @param id of the object to be found.
     * @return found object of gift certificate
     */
    GiftCertificate findById(long id);

    /**
     * Finds gift certificates by criteria.
     *
     * @param certificateName the name criteria.
     * @param tagName the name of tag connected with certificate.
     * @param description criteria.
     * @param sortByDate criteria.
     * @param sortByName criteria.
     * @return list of the found gift certificates.
     */
    List<GiftCertificate> findAll(String certificateName, String tagName, String description,
                                            String sortByDate, String sortByName);
}
