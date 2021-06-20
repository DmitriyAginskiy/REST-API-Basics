package com.epam.esm.controller;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @return ResponseEntity object with some information about creating and response status.
     */
    @PostMapping
    public ResponseEntity<String> createGiftCertificate(@RequestBody GiftCertificate certificate) {
        certificateService.insert(certificate);
        return ResponseEntity.status(HttpStatus.CREATED).body("Certificate created successfully");
    }

    /**
     * Finds all gift certificates.
     *
     * @return list with gift certificates.
     */
    @GetMapping
    public List<GiftCertificate> findAllGiftCertificates() {
        return certificateService.findAll();
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
    @GetMapping
    public List<GiftCertificate> findAllCertificatesByCriteria(@RequestParam(required = false) String certificateName,
                                                               @RequestParam(required = false) String tagName,
                                                               @RequestParam(required = false) String description,
                                                               @RequestParam(required = false) String sortByDate,
                                                               @RequestParam(required = false) String sortByName) {
        return certificateService.findAllByCriteria(certificateName, tagName, description, sortByDate, sortByName);
    }

    /**
     * Finds gift certificate by id
     *
     * @param id the id of certificate to be found.
     * @return found gift certificate object.
     */
    @GetMapping("/{id}")
    public GiftCertificate findGiftCertificateById(@PathVariable long id) {
        return certificateService.findById(id);
    }

    /**
     * Deletes gift certificate.
     *
     * @param id the id of certificate to be deleted.
     * @return Response entity with removal information.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGiftCertificate(@PathVariable long id) {
        certificateService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Certificate deleted successfully");
    }

    /**
     * Updates gift certificate.
     *
     * @param id the id of certificate to be updated.
     * @return Updated object.
     */
    @PutMapping("/{id}")
    public GiftCertificate updateGiftCertificate(@PathVariable long id, @RequestBody GiftCertificate certificate) {
        return certificateService.update(id, certificate);
    }
}
