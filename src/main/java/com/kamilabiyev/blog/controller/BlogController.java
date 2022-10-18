package com.kamilabiyev.blog.controller;

import com.kamilabiyev.blog.model.dto.BlogDTO;
import com.kamilabiyev.blog.model.request.blog.AddBlogRequest;
import com.kamilabiyev.blog.model.request.blog.FilterBlogRequest;
import com.kamilabiyev.blog.model.request.blog.UpdateBlogRequest;
import com.kamilabiyev.blog.service.BlogService;
import com.kamilabiyev.blog.utils.FileUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BlogController {
    BlogService blogService;
    FileUtil fileUtil;

    @GetMapping("")
    @SecurityRequirements
    public ResponseEntity<List<BlogDTO>> getAll(final FilterBlogRequest request) {
        return new ResponseEntity<>(blogService.getAll(request), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @SecurityRequirements
    public ResponseEntity<BlogDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(blogService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Long> add(
            @Valid @ModelAttribute AddBlogRequest request) {
        return new ResponseEntity<>(blogService.add(request), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Long> update(
            @Valid @ModelAttribute UpdateBlogRequest request,
            @PathVariable Long id) {
        return new ResponseEntity<>(blogService.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(
            @Valid @PathVariable Long id) {
        blogService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
