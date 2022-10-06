package com.kamilabiyev.blog.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateBlogRequest {
    private String title;
    private String content;
    private Long categoryId;
    private MultipartFile file;
}
