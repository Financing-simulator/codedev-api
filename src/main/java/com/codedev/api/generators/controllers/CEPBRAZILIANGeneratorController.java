package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.CEPBRAZILIANGeneratorService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/cep")
public class CEPBRAZILIANGeneratorController {

    @GetMapping("/generate-cep")
    public ResponseEntity<String> generateRandomCEP() {
        String generatedCEP = CEPBRAZILIANGeneratorService.generatePostalCode();

        return ResponseEntity.ok(generatedCEP);
    }

    @GetMapping("/generate-cep")
    public ResponseEntity<String> generateRandomCEPState(@RequestParam("state") String state) {
        String generatedCEP = CEPBRAZILIANGeneratorService.generatePostalCode(state.toUpperCase());

        if (!CEPBRAZILIANGeneratorService.isCEPValid(generatedCEP)) {
            return ResponseEntity.badRequest().body("Invalid or not supported state.");
        }

        return ResponseEntity.ok(generatedCEP);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateCEP(@RequestParam("cep") String cep) {
        return ResponseEntity.ok(CEPBRAZILIANGeneratorService.isCEPValid(cep));
    }

}
