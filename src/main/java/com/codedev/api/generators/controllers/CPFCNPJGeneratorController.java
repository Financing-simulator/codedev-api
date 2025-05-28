package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.CPFCNPJGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/cpf-cnpj")
public class CPFCNPJGeneratorController {

    @GetMapping("/generate-cpf")
    public ResponseEntity<String> generateRandomCPF() {
        String generatedCPF = CPFCNPJGeneratorService.generateRandomCPF();

        return ResponseEntity.ok(generatedCPF);
    }

    @GetMapping("/generate-cnpj")
    public ResponseEntity<String> generateRandomCNPJ() {
        String generatedCNPJ = CPFCNPJGeneratorService.generateRandomCNPJ();

        return ResponseEntity.ok(generatedCNPJ);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateCPFOrCNPJ(@RequestParam(name = "cpf") String value) {
        boolean isValid = value.length() < 14 ? CPFCNPJGeneratorService.isValidCPF(value) : CPFCNPJGeneratorService.isValidCNPJ(value);

        return ResponseEntity.ok(isValid);
    }

}
