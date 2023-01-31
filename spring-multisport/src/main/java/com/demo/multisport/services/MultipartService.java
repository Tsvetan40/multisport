package com.demo.multisport.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MultipartService {
    String save(MultipartFile file);

    default String getType(String pathFile) {
        int i = pathFile.lastIndexOf('.');
        return pathFile.substring(i + 1);
    }

    byte[] getFileBytes(String pathFile) throws IOException;
}
