package com.codedev.api.internet.controllers;

import com.codedev.api.internet.services.InternetClientIPService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class InternetClientIPController {

    @Autowired
    InternetClientIPService internetClientIPService;

    @GetMapping("/get-client-current-ip")
    public ResponseEntity<String> getClientCurrentIP(HttpServletRequest httpServletRequest) {
        String currentIP = internetClientIPService.getClientIp(httpServletRequest);

        return ResponseEntity.ok(currentIP);
    }

}
