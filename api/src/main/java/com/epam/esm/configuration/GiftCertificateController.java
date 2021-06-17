package com.epam.esm.configuration;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {

    private final GiftCertificateService certificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createGiftCertificate(@RequestBody GiftCertificate certificate) {
        certificateService.insert(certificate);
        return ResponseEntity.status(HttpStatus.CREATED).body("Certificate created successfully");
    }

    @GetMapping("/all")
    public List<GiftCertificate> findAllGiftCertificates() {
        return certificateService.findAll();
    }

    @GetMapping
    public List<GiftCertificate> findAllCertificatesByCriteria(@RequestParam(required = false) String certificateName,
                                                               @RequestParam(required = false) String tagName,
                                                               @RequestParam(required = false) String description,
                                                               @RequestParam(required = false) String sortByDate,
                                                               @RequestParam(required = false) String sortByName) {
        return certificateService.findAllByCriteria(certificateName, tagName, description, sortByDate, sortByName);
    }

    @GetMapping("/{id}")
    public GiftCertificate findGiftCertificateById(@PathVariable long id) {
        return certificateService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGiftCertificate(@PathVariable long id) {
        certificateService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Certificate deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGiftCertificate(@PathVariable long id, @RequestBody GiftCertificate certificate) {
        certificateService.update(id, certificate);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Certificate updated successfully");
    }
}
