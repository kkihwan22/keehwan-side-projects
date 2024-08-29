package com.keehwan.share.utils;

import java.security.SecureRandom;

public class RandomCodeGenerator {
    private static final SecureRandom secureRandom = new SecureRandom(); // SecureRandom 객체를 static final로 생성하여 재사용
    private static final String NUMBERS = "0123456789";
    private static final String LOWER_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
    private static final String SPECIAL_CHARACTERS = "@$!%*?$";

    public static int generateRandomNumber(int digit) {
        return secureRandom.nextInt((int) Math.pow(10, digit));
    }

    public static int generateRangeRandomNumber(int start, int end) {
        return start + secureRandom.nextInt(end + 1);
    }

    public static String generateRandomNumberString(int digit) {
        return generate(NUMBERS, digit);
    }

    public static String generateRandomLowercaseString(int digit) {
        return generate(LOWER_ALPHABET, digit);
    }

    public static String generateRandomUppercaseString(int digit) {
        return generate(UPPERCASE_ALPHABET, digit);
    }

    public static String generateRandomAlphabet(int digit) {
        String source = UPPERCASE_ALPHABET + LOWER_ALPHABET;
        return generate(source, digit);
    }

    public static String generateRandomString(int digit) {
        String source = UPPERCASE_ALPHABET + LOWER_ALPHABET + SPECIAL_CHARACTERS + NUMBERS;
        return generate(source, digit);
    }

    private static String generate(String source, int digit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            sb.append(source.charAt(secureRandom.nextInt(source.length())));
        }
        return sb.toString();
    }
}
