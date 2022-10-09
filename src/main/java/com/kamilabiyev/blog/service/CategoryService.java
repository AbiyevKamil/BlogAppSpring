package com.kamilabiyev.blog.service;

import com.kamilabiyev.blog.model.dto.CategoryDTO;
import com.kamilabiyev.blog.model.request.category.AddCategoryRequest;
import com.kamilabiyev.blog.model.request.category.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {
    Long add(AddCategoryRequest request);

    Long update(Long id, UpdateCategoryRequest request);

    void delete(Long id);

    CategoryDTO getById(Long id);

    List<CategoryDTO> get();
}
