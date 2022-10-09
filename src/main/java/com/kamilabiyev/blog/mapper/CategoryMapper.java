package com.kamilabiyev.blog.mapper;

import com.kamilabiyev.blog.model.dto.CategoryDTO;
import com.kamilabiyev.blog.model.entity.Category;
import com.kamilabiyev.blog.model.request.category.AddCategoryRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }

    public List<CategoryDTO> toCategoryDTOList(List<Category> categories) {
        return categories.stream().map(this::toCategoryDTO).collect(Collectors.toList());
    }

    public Category toCategory(AddCategoryRequest blog) {
        return new Category(
                blog.getName()
        );
    }
}
