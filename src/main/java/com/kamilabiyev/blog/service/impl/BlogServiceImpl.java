package com.kamilabiyev.blog.service.impl;

import com.kamilabiyev.blog.model.dto.BlogDTO;
import com.kamilabiyev.blog.model.request.AddBlogRequest;
import com.kamilabiyev.blog.model.request.UpdateBlogRequest;
import com.kamilabiyev.blog.repository.BlogRepository;
import com.kamilabiyev.blog.repository.CategoryRepository;
import com.kamilabiyev.blog.repository.FileRepository;
import com.kamilabiyev.blog.service.BlogService;
import com.kamilabiyev.blog.utils.FileUtil;
import com.kamilabiyev.blog.utils.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogServiceImpl implements BlogService {
    BlogRepository blogRepository;
    FileRepository fileRepository;
    CategoryRepository categoryRepository;
    FileUtil fileUtil;

    @Override
    public Long add(AddBlogRequest request) {
        return null;
    }

    @Override
    public Long update(Long id, UpdateBlogRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<BlogDTO> getAll() {
        return null;
    }

    @Override
    public BlogDTO getById(Long id) {
        return null;
    }
}
