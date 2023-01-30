package com.demo.multisport.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface MultipartService {
    void save(MultipartFile file);
    Set<MultipartFile> getFiles(String directory);
}
