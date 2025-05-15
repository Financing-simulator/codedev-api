package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.CEPBRAZILIANGeneratorService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("*")
public class CEPBRAZILIANGeneratorController {

    @GetMapping("/generate-randon-cep")
    public ResponseEntity<String> generateRandomCEP() {
        String generatedCEP = CEPBRAZILIANGeneratorService.generatePostalCode();

        return ResponseEntity.ok(generatedCEP);
    }

    @GetMapping("/generate-random-cep/{state}")
    public ResponseEntity<String> generateRandomCEPState(@PathParam("state") String state) {
        String generatedCEP = CEPBRAZILIANGeneratorService.generatePostalCode(state.toUpperCase());

        // Regex to identify if the generated CEP is valid.
        Pattern pattern = Pattern.compile("\\d{5}-\\d{3}");
        Matcher matcher = pattern.matcher(generatedCEP);

        if (!matcher.find()) {
            return ResponseEntity.badRequest().body("Invalid or not supported state.");
        }

        return ResponseEntity.ok(generatedCEP);
    }

}
