package com.kamilabiyev.blog.utils;

import com.kamilabiyev.blog.exception.CustomException;
import com.kamilabiyev.blog.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUtil {

    private final FileProperties fileProperties;

    public String upload(MultipartFile multipartFile) {
        try {
            if (multipartFile.isEmpty())
                throw new CustomException("File is required.", HttpStatus.BAD_REQUEST);

            File f = new File(fileProperties.getUploadFolder());
            if (!f.exists() || !f.isDirectory()) f.mkdirs();

            String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename();
            String pathName = fileProperties.getUploadFolder() + fileName;
            File file = new File(pathName);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(multipartFile.getBytes());
            }
            return fileName;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new CustomException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(String filePath) {
        var file = new File(fileProperties.getUploadFolder() + filePath);
        if (file.exists()) file.delete();
    }
}
