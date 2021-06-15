package com.epam.esm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {
    @GetMapping("/numbers")
    public ResponseEntity<String> getNumbers() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Certificate created successfully");
    }
}
