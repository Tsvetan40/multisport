package com.demo.multisport.services.impl;

import com.demo.multisport.services.MultipartService;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

@Service
public class PlanMultipartService implements MultipartService {
    private final String DIR_PLAN = "plan";
    @Override
    public void save(MultipartFile file){
        File dir = new File(DIR_PLAN);
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
    }

    @Override
    public Set<MultipartFile> getFiles(String directory) {
        return null;
    }
}
