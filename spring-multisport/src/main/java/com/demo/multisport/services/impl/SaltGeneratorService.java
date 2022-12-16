package com.demo.multisport.services.impl;

import com.demo.multisport.services.Generator;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class SaltGeneratorService implements Generator {
    private static SecureRandom random = new SecureRandom();

    @Override
    public String generate() {
        char[] salt = new char[10];
        for (int i = 0; i < salt.length; i++) {
            salt[i] = (char) (random.nextInt(126 - 32) + 32);
        }

        return new String(salt);
    }
}