package com.codedev.api.generators.controllers;

import com.codedev.api.generators.services.GTINEAN13GeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class GTINEAN13GeneratorController {

    @GetMapping("/generate-gtin-ean")
    public ResponseEntity<String> generateRandomGTINEAN13() {
        String gtin = GTINEAN13GeneratorService.generateRandomGTINEAN13();

        return ResponseEntity.ok(gtin);
    }

}
