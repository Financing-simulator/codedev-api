package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.CPFCNPJGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class CPFCNPJGeneratorController {

    @Autowired
    CPFCNPJGeneratorService cpfCnpjGeneratorService;

    @GetMapping("/generate-random-cpf")
    public ResponseEntity<String> generateRandomCPF() {
        String generatedCPF = cpfCnpjGeneratorService.generateRandomCPF();

        return ResponseEntity.ok(generatedCPF);
    }

    @GetMapping("/generate-random-cnpj")
    public ResponseEntity<String> generateRandomCNPJ() {
        String generatedCNPJ = cpfCnpjGeneratorService.generateRandomCNPJ();

        return ResponseEntity.ok(generatedCNPJ);
    }
}
