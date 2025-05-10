package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.PasswordGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class PasswordGenerator {

    @GetMapping("/generate-password/{length}")
    public ResponseEntity<String> generatePassword(@PathVariable("length") int length) {
        String generatedPassword = PasswordGeneratorService.generatePassword(length);

        return ResponseEntity.ok(generatedPassword);
    }

    @GetMapping("/generate-password")
    public ResponseEntity<String> generatePassword() {
        String generatedPassword = PasswordGeneratorService.generatePassword();

        return ResponseEntity.ok(generatedPassword);
    }

}
