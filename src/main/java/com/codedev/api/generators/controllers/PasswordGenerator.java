package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.PasswordGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/password")
public class PasswordGenerator {

    @GetMapping("/generate")
    public ResponseEntity<String> generatePassword() {
        String generatedPassword = PasswordGeneratorService.generatePassword();

        return ResponseEntity.ok(generatedPassword);
    }

    @GetMapping("/generate/set-length")
    public ResponseEntity<String> generatePassword(@RequestParam("length") int length) {
        String generatedPassword = PasswordGeneratorService.generatePassword(length);

        return ResponseEntity.ok(generatedPassword);
    }

}
