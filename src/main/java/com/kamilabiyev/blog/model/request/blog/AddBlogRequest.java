package com.kamilabiyev.blog.model.request.blog;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddBlogRequest {
    private Long id;
    private String title;
    private String content;
    private Long categoryId;
    private MultipartFile file;
}
