package com.demo.multisport.services.impl;

import com.demo.multisport.services.MultipartService;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

public class PlanMultipart implements MultipartService {
    private final String DIR_PLAN;
    private final File dir;

    public PlanMultipart() {
        this.DIR_PLAN = "plan";
        this.dir = new File(DIR_PLAN);
    }
    @Override
    public String save(MultipartFile file){
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String path = dir.getAbsolutePath() + "/" + file.getOriginalFilename();
        File newFile = new File(path);
        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public byte[] getFileBytes(String pathFile) throws IOException {
        File file = new File(pathFile);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] byteArray = new byte[(int) file.length()];
        inputStream.read(byteArray);
        return byteArray;
    }
}
