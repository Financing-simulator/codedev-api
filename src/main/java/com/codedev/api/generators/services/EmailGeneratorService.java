package com.codedev.api.generators.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class EmailGeneratorService {
    private static final String DEFAULT_DOMAIN = "example.com";
    private static final String CHAR_POOL = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");

    private List<String> domains;
    private String prefix = "";
    private String suffix = "";
    private int randomPartLength = 8;

    private final Random random = new Random();

    public void setDomains(List<String> domains) {
        this.domains = domains != null && !domains.isEmpty() ? domains : List.of(DEFAULT_DOMAIN);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix != null ? prefix : "";
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix != null ? suffix : "";
    }

    public void setRandomPartLength(int length) {
        if (length > 0) {
            this.randomPartLength = length;
        }
    }

    public String generate() {
        String randomPart = generateRandomString(randomPartLength);
        String localPart = prefix + randomPart + suffix;
        localPart = localPart.replaceAll("[^a-zA-Z0-9_.+-]", "");

        String domain = domains.get(random.nextInt(domains.size()));
        String email = localPart + "@" + domain;

        if (!isEmailValid(email)) {
            throw new RuntimeException("Invalid e-mail: " + email);
        }

        return email;
    }

    public boolean isEmailValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            builder.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }

        return builder.toString();
    }

}

