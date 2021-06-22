package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * Gift certificates controller class.
 *
 * @author Dzmitry Ahinski
 */
@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {

    private final GiftCertificateService certificateService;

    /**
     * Init the gift certificates controller class.
     *
     * @author Dzmitry Ahinski
     */
    @Autowired
    public GiftCertificateController(GiftCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * Create a new gift certificate;
     *
     * @param certificate an object to be created
     * @return created gift certificate object
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public GiftCertificate createGiftCertificate(@RequestBody GiftCertificate certificate) {
        return certificateService.insert(certificate);
    }

    /**
     * Finds all gift certificates by criteria
     *
     * @param certificateName the name of certificate to be found.
     * @param tagName the tag name of certificate to be found.
     * @param description the description of certificate to be found.
     * @param sortByDate the sort param for creation date.
     * @param sortByName the sort param for name.
     * @return list with found items.
     */
    @GetMapping(produces = "application/json; charset=utf-8")
    public List<GiftCertificate> findAllGiftCertificates(@RequestParam(required = false) String certificateName,
                                                               @RequestParam(required = false) String tagName,
                                                               @RequestParam(required = false) String description,
                                                               @RequestParam(required = false) String sortByDate,
                                                               @RequestParam(required = false) String sortByName) {
        return certificateService.findAll(certificateName, tagName, description, sortByDate, sortByName);
    }

    /**
     * Finds gift certificate by id
     *
     * @param id of certificate to be found.
     * @return found gift certificate object.
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public GiftCertificate findGiftCertificateById(@PathVariable long id) {
        return certificateService.findById(id);
    }

    /**
     * Deletes gift certificate.
     *
     * @param id of certificate to be deleted.
     * @return Response entity with NO CONTENT status
     */
    @DeleteMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> deleteGiftCertificate(@PathVariable long id) {
        certificateService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    /**
     * Updates gift certificate.
     *
     * @param id the id of certificate to be updated.
     * @param certificate with new fields for update.
     * @return Updated object.
     */
    @PatchMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public GiftCertificate updateGiftCertificate(@PathVariable long id, @RequestBody GiftCertificate certificate) {
        return certificateService.update(id, certificate);
    }
}
