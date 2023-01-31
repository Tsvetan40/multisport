package com.demo.multisport.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MultipartService {
    String save(MultipartFile file);
    byte[] getFileBytes(String pathFile) throws IOException;
}
