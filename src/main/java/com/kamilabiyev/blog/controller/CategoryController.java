package com.kamilabiyev.blog.controller;


import com.kamilabiyev.blog.model.dto.CategoryDTO;
import com.kamilabiyev.blog.model.request.category.AddCategoryRequest;
import com.kamilabiyev.blog.model.request.category.UpdateCategoryRequest;
import com.kamilabiyev.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @GetMapping("/")
    @SecurityRequirements
    public ResponseEntity<List<CategoryDTO>> get() {
        return ResponseEntity.ok(categoryService.get());
    }

    @GetMapping("/{id}")
    @SecurityRequirements
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Long> add(@Valid @RequestBody AddCategoryRequest request) {
        return ResponseEntity.ok(categoryService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id,
                                       @Valid @RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
