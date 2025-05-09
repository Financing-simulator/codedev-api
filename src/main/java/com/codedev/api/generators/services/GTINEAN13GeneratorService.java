package com.codedev.api.generators.services;

import java.util.Random;

public class GTINEAN13GeneratorService {

    public static String generateRandomGTINEAN13() {
        int[] gtin = new int[13];
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            gtin[i] = random.nextInt(10);
        }

        gtin[12] = calculateVerifierDigit(gtin);

        StringBuilder sb = new StringBuilder();

        for (int digit : gtin) {
            sb.append(digit);
        }

        return sb.toString();
    }

    private static int calculateVerifierDigit(int[] gtin) {
        int sum = 0;

        for (int i = 0; i < 12; i++) {
            sum += gtin[i] * ((i % 2 == 0) ? 1 : 3);
        }

        int rest = sum % 10;
        return (rest == 0) ? 0 : 10 - rest;
    }

}
