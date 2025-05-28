package com.codedev.api.generators.services;

import java.util.Random;

public class CPFCNPJGeneratorService {

    public static String generateRandomCPF() {
        int[] cpf = new int[11];
        Random random = new Random();

        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }

        cpf[9] = calculateVerifierDigit(cpf, 10);
        cpf[10] = calculateVerifierDigit(cpf, 11);

        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d",
                cpf[0], cpf[1], cpf[2],
                cpf[3], cpf[4], cpf[5],
                cpf[6], cpf[7], cpf[8],
                cpf[9], cpf[10]
        );
    }

    public static String generateRandomCNPJ() {
        int[] cnpj = new int[14];
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            cnpj[i] = random.nextInt(10);
        }

        cnpj[8] = 0;
        cnpj[9] = 0;
        cnpj[10] = 0;
        cnpj[11] = 1;
        cnpj[12] = calculateVerifierDigit(cnpj, new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});
        cnpj[13] = calculateVerifierDigit(cnpj, new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});

        return String.format("%d%d.%d%d%d.%d%d%d/%d%d%d%d-%d%d",
                cnpj[0], cnpj[1], cnpj[2], cnpj[3], cnpj[4],
                cnpj[5], cnpj[6], cnpj[7], cnpj[8], cnpj[9],
                cnpj[10], cnpj[11], cnpj[12], cnpj[13]
        );
    }

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}") && !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"))
            return false;

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.chars().distinct().count() == 1) return false;

        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int dv1 = calculateVerifierDigit(digits, 10);
        int dv2 = calculateVerifierDigit(digits, 11);

        return digits[9] == dv1 && digits[10] == dv2;
    }

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}") && !cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}"))
            return false;

        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.chars().distinct().count() == 1) return false;

        int[] digits = new int[14];

        for (int i = 0; i < 14; i++) {
            digits[i] = Character.getNumericValue(cnpj.charAt(i));
        }

        int dv1 = calculateVerifierDigit(digits, new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});
        int dv2 = calculateVerifierDigit(digits, new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});

        return digits[12] == dv1 && digits[13] == dv2;
    }

    private static int calculateVerifierDigit(int[] cpf, int initialWeight) {
        int sum = 0;

        for (int i = 0; i < initialWeight - 1; i++) {
            sum += cpf[i] * (initialWeight - i);
        }

        int rest = sum % 11;
        return (rest < 2) ? 0 : 11 - rest;
    }

    private static int calculateVerifierDigit(int[] cnpj, int[] weights) {
        int sum = 0;

        for (int i = 0; i < weights.length; i++) {
            sum += cnpj[i] * weights[i];
        }

        int rest = sum % 11;
        return (rest < 2) ? 0 : 11 - rest;
    }

}
