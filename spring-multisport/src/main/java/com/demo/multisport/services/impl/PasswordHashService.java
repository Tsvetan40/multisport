package com.demo.multisport.services.impl;

import com.demo.multisport.services.HashGenerator;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


@Service
public class PasswordHashService implements HashGenerator {
    @Override
    public String hash(String value, String salt) {
        PBEKeySpec keySpec = new PBEKeySpec(value.toCharArray(), salt.getBytes(), 5646, 104);
        SecretKeyFactory skf;
        SecretKey secretKey;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            secretKey = skf.generateSecret(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }

        return Hex.encodeHexString(secretKey.getEncoded());
    }
}
