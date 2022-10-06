package com.kamilabiyev.blog.utils;

import com.kamilabiyev.blog.exception.CustomException;
import com.kamilabiyev.blog.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Component
@RequiredArgsConstructor
public class FileUtil {

    private final FileProperties fileProperties;

    public String upload(MultipartFile multipartFile) {
        try {
            if (multipartFile.isEmpty())
                throw new CustomException("File is required.", HttpStatus.BAD_REQUEST);

            String fileName = multipartFile.getOriginalFilename();
            File file = new File(fileProperties.getUploadFolder() + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            return fileName;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
