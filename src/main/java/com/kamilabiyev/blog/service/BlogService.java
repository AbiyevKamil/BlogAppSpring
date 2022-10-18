package com.kamilabiyev.blog.service;

import com.kamilabiyev.blog.model.dto.BlogDTO;
import com.kamilabiyev.blog.model.request.blog.AddBlogRequest;
import com.kamilabiyev.blog.model.request.blog.FilterBlogRequest;
import com.kamilabiyev.blog.model.request.blog.UpdateBlogRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {
    Long add(AddBlogRequest request);
    Long update(Long id, UpdateBlogRequest request);
    void delete(Long id);
    List<BlogDTO> getAll(FilterBlogRequest request);
    BlogDTO getById(Long id);
}
