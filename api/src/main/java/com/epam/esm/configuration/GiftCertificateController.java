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
        boolean isCreated = certificateService.insert(certificate);
        if(isCreated) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Certificate created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Certificate is not created!");
        }
    }

    @GetMapping("/all")
    public List<GiftCertificate> findAllGiftCertificate() {
        return certificateService.findAll();
    }

    @GetMapping("/{id}")
    public GiftCertificate findGiftCertificateById(@PathVariable long id) {
        System.out.println(certificateService.findById(id).get());
        return certificateService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGiftCertificate(@PathVariable long id) {
        boolean isDeleted = certificateService.delete(id);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Certificate deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Certificate is not deleted!");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateGiftCertificate(@PathVariable long id, @RequestBody GiftCertificate certificate) {
        boolean isUpdated = certificateService.update(id, certificate);
        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Certificate updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Certificate is not updated!");
        }
    }
}
