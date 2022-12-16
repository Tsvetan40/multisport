package com.demo.multisport.services;

public interface HashGenerator {
    String hash(String value, String salt);
}
