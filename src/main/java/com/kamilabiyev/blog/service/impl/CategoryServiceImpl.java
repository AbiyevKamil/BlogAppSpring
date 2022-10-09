package com.kamilabiyev.blog.service.impl;

import com.kamilabiyev.blog.exception.CustomException;
import com.kamilabiyev.blog.mapper.CategoryMapper;
import com.kamilabiyev.blog.model.dto.CategoryDTO;
import com.kamilabiyev.blog.model.request.category.AddCategoryRequest;
import com.kamilabiyev.blog.model.request.category.UpdateCategoryRequest;
import com.kamilabiyev.blog.repository.CategoryRepository;
import com.kamilabiyev.blog.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {

    CategoryMapper categoryMapper;
    CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Long add(AddCategoryRequest request) {
        var category = categoryMapper.toCategory(request);
        var exists = categoryRepository.findCategoryByName(request.getName());
        if (exists.isPresent()) return exists.get().getId();
        var saved = categoryRepository.save(category);
        return saved.getId();
    }

    @Override
    @Transactional
    public Long update(Long id, UpdateCategoryRequest request) {
        var exists = categoryRepository.findCategoryByName(request.getName());
        if (exists.isEmpty())
            throw new CustomException(String.format("Category: %d doesn't exist.", id),
                    HttpStatus.NOT_FOUND);
        var category = exists.get();
        category.setName(request.getName());
        var saved = categoryRepository.save(category);
        return saved.getId();
    }

    @Override
    public void delete(Long id) {
        var exists = categoryRepository.findById(id);
        if (exists.isEmpty())
            throw new CustomException(String.format("Category: %d doesn't exist.", id),
                    HttpStatus.NOT_FOUND);
        categoryRepository.delete(exists.get());
    }

    @Override
    public CategoryDTO getById(Long id) {
        var exists = categoryRepository.findById(id);
        if (exists.isEmpty())
            throw new CustomException(String.format("Category: %d doesn't exist.", id),
                    HttpStatus.NOT_FOUND);
        return categoryMapper.toCategoryDTO(exists.get());
    }

    @Override
    public List<CategoryDTO> get() {
        var categories = categoryRepository.findAll();
        return categoryMapper.toCategoryDTOList(categories);
    }
}
