package com.codedev.api.generators.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CPFCNPJGeneratorService {

    public String generateRandomCPF() {
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

    public String generateRandomCNPJ() {
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
