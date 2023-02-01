package com.demo.multisport.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MultipartUtilImpl {
    private final static String DIR_PLAN = "plan";
    private final static String DIR_ARTICLES = "article";

    private static String save(MultipartFile file, File dir) {
        String path = dir.getAbsolutePath() + "/" + file.getOriginalFilename();
        File newFile = new File(path);

        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String savePlan(MultipartFile file){
        File dir = new File(DIR_PLAN);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return save(file, dir);
    }

    public static String saveArticle(MultipartFile file) {
        File dir = new File(DIR_ARTICLES);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return save(file, dir);
    }

    private static byte[] getFileBytes(String pathFile) throws IOException {
        File file = new File(pathFile);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] byteArray = new byte[(int) file.length()];
        inputStream.read(byteArray);
        return byteArray;
    }

    private static String getType(String pathFile) {
        int i = pathFile.lastIndexOf('.');
        return pathFile.substring(i + 1);
    }


    public static String convertFileToBase64(String pathFile) throws  IOException{

        String fileContent = Base64.encodeBase64String(getFileBytes(pathFile));
        String type = getType(pathFile);

        return String.format("data:image/%s;base64,%s", type, fileContent);
    }
}
