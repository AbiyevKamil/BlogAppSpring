package com.kamilabiyev.blog.controller;

import com.kamilabiyev.blog.model.dto.BlogDTO;
import com.kamilabiyev.blog.model.request.blog.AddBlogRequest;
import com.kamilabiyev.blog.model.request.blog.UpdateBlogRequest;
import com.kamilabiyev.blog.service.BlogService;
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

    @GetMapping("")
    @SecurityRequirements
    public ResponseEntity<List<BlogDTO>> getAll() {
        return new ResponseEntity<>(blogService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @SecurityRequirements
    public ResponseEntity<BlogDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<BlogDTO>(blogService.getById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> add(
            @Valid @RequestBody AddBlogRequest request) {
        return new ResponseEntity<>(blogService.add(request), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Long> update(
            @Valid @RequestBody UpdateBlogRequest request,
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
