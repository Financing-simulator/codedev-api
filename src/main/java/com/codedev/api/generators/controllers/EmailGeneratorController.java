package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.EmailGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EmailGeneratorController {

    @Autowired
    EmailGeneratorService emailGeneratorService;

    @GetMapping("/generate-email")
    public ResponseEntity<String> generateValidEmail() {
        try {
            emailGeneratorService.setPrefix("codedev.");
            emailGeneratorService.setRandomPartLength(8);

            String generatedEmail = emailGeneratorService.generate();

            return ResponseEntity.ok(generatedEmail);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/generate-email/domains")
    public ResponseEntity<String> generateValidEmailWithDomains(@RequestBody List<String> domains) {
        try {
            emailGeneratorService.setDomains(domains);
            emailGeneratorService.setRandomPartLength(8);

            String generatedEmail = emailGeneratorService.generate();

            return ResponseEntity.ok(generatedEmail);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
