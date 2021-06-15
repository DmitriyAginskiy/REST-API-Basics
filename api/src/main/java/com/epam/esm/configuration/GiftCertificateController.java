package com.epam.esm.configuration;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {

    private final GiftCertificateService certificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> getNumbers(@RequestBody GiftCertificate certificate) {
        certificateService.insert(certificate);
        return ResponseEntity.status(HttpStatus.CREATED).body("Certificate created successfully");
    }
}
