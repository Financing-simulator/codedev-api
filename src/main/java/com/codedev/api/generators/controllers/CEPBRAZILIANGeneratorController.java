package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.CEPBRAZILIANGeneratorService;
import com.codedev.api.generators.services.CPFCNPJGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class CEPBRAZILIANGeneratorController {

    @GetMapping("/generate-randon-cep")
    public ResponseEntity<String> generateRandomCEP() {
        String generatedCEP = CEPBRAZILIANGeneratorService.generatePostalCode();
        return ResponseEntity.ok(generatedCEP);
    }

    @GetMapping("/generate-randon-cep-estado")
    public ResponseEntity<String> generateRandomCEPState(@RequestParam(required = true) String estado) {
        String generatedCEP = CEPBRAZILIANGeneratorService.generatePostalCode(estado);
        return ResponseEntity.ok(generatedCEP);
    }


}
