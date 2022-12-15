package com.demo.multisport.services.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordHashServiceTest {
    PasswordHashService hashService;
    SaltGeneratorService saltService;

    @Autowired
    PasswordHashServiceTest(PasswordHashService hashService, SaltGeneratorService saltService) {
        this.hashService = hashService;
        this.saltService = saltService;
    }

    @Test
    void lengthHashTest() {
        String salt1 = saltService.generate();
        String salt2 = saltService.generate();

        Assertions.assertTrue(salt1.length() == 10, "Length of password is not 10");
        Assertions.assertTrue(salt1.length() == salt2.length(), "Lengths of salt are different");

        String password1 = "password";
        String password2 = "1234567890";

        Assertions.assertTrue(hashService.hash(password1, salt1).length() == 26, "Length of hash password is not 26");
        Assertions.assertTrue(hashService.hash(password1, salt2).length() == 26, "Length of hash password is not 26");
        Assertions.assertTrue(hashService.hash(password2, salt1).length() == 26, "Length of hash password is not 26");
        Assertions.assertTrue(hashService.hash(password2, salt2).length() == 26, "Length of hash password is not 26");

    }
}
