package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.EmailGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/email")
public class EmailGeneratorController {

    @Autowired
    EmailGeneratorService emailGeneratorService;

    @GetMapping("/generate")
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

    @PostMapping("/generate/domains")
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

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(emailGeneratorService.isEmailValid(email));
    }

}
