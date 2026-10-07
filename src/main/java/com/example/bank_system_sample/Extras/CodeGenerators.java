package com.example.bank_system_sample.Extras;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.UUID;

@Component
public class CodeGenerators {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public String codeForCustomer() {
        return "ID-" + random(6);
    }

    public String accountCode(String prefix) {
        return prefix + "-" + random(6);
    }

    public String transactionCode() {
        return "TX-" + random(12);
    }

    private String random(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}
