package com.epam.esm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {
    @GetMapping("/numbers")
    public List<Integer> getNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
