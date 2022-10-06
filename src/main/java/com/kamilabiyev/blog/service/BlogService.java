package com.kamilabiyev.blog.service;

import com.kamilabiyev.blog.model.dto.BlogDTO;
import com.kamilabiyev.blog.model.request.AddBlogRequest;
import com.kamilabiyev.blog.model.request.UpdateBlogRequest;

import java.util.List;

public interface BlogService {
    Long add(AddBlogRequest request);
    Long update(Long id, UpdateBlogRequest request);
    void delete(Long id);
    List<BlogDTO> getAll();
    BlogDTO getById(Long id);
}
