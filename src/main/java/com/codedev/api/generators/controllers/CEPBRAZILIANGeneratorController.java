package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.CEPBRAZILIANGeneratorService;
import com.codedev.api.generators.services.CPFCNPJGeneratorService;
import jakarta.websocket.server.PathParam;
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

    @GetMapping("/generate-random-cep-estado")
    public ResponseEntity<String> generateRandomCEPState(@PathParam("estado") String estado) {
        String generatedCEP = CEPBRAZILIANGeneratorService.generatePostalCode(estado.toUpperCase());

        if (generatedCEP == null) {
            return ResponseEntity.badRequest().body("Estado inválido ou não suportado.");
        }

        return ResponseEntity.ok(generatedCEP);
    }


}
