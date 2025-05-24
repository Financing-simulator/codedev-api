package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.CEPBRAZILIANGeneratorService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

        if (!CEPBRAZILIANGeneratorService.isCEPValid(generatedCEP)) {
            return ResponseEntity.badRequest().body("Invalid or not supported state.");
        }

        return ResponseEntity.ok(generatedCEP);
    }

    @GetMapping("/validate-cep/{cep}")
    public ResponseEntity<Boolean> validateCEP(@PathVariable("cep") String cep) {
        return ResponseEntity.ok(CEPBRAZILIANGeneratorService.isCEPValid(cep));
    }

}
