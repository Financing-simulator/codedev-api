package com.codedev.api.generators.services;

import java.security.SecureRandom;

public class PasswordGeneratorService {

    private static final char[] CHARACTERS = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            '0','1','2','3','4','5','6','7','8','9',
            '~','`','!','@','#','£','€','$','(',')','*','^','&','°','%','§','¥','¢','?','.',',','<','>','\'','"',';',':','/','\\','|','[',']','{','}','=','+','_','-'
    };
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length);
            password.append(CHARACTERS[index]);
        }

        return password.toString();
    }

    public static String generatePassword() {
        int length = calculateRandomLength();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length);
            password.append(CHARACTERS[index]);
        }

        return password.toString();
    }

    private static int calculateRandomLength() {
        int minLength = 8;
        int maxLength = CHARACTERS.length - (minLength * 3);
        int randomNumber = random.nextInt(maxLength);

        return Math.max(randomNumber, minLength);
    }
}
