package com.kamilabiyev.blog.service.impl;

import com.kamilabiyev.blog.exception.CustomException;
import com.kamilabiyev.blog.mapper.BlogMapper;
import com.kamilabiyev.blog.model.dto.BlogDTO;
import com.kamilabiyev.blog.model.entity.FileEntity;
import com.kamilabiyev.blog.model.request.blog.AddBlogRequest;
import com.kamilabiyev.blog.model.request.blog.UpdateBlogRequest;
import com.kamilabiyev.blog.repository.BlogRepository;
import com.kamilabiyev.blog.repository.CategoryRepository;
import com.kamilabiyev.blog.repository.FileRepository;
import com.kamilabiyev.blog.repository.UserRepository;
import com.kamilabiyev.blog.service.BlogService;
import com.kamilabiyev.blog.utils.FileUtil;
import com.kamilabiyev.blog.utils.SecurityUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogServiceImpl implements BlogService {
    BlogRepository blogRepository;
    UserRepository userRepository;
    FileRepository fileRepository;
    BlogMapper blogMapper;
    CategoryRepository categoryRepository;
    FileUtil fileUtil;

    @Override
    public Long add(AddBlogRequest request) {
        var blog = blogMapper.toBlog(request);
        var userPrincipal = SecurityUtil.getPrincipal();
        var userOptional = userRepository.findByUsername(userPrincipal.get().getUsername());
        if (userOptional.isEmpty()) throw new CustomException("User not found.", HttpStatus.NOT_FOUND);
        var user = userOptional.get();
        blog.setUser(user);
        var categoryOptional = categoryRepository.findById(request.getCategoryId());
        if (categoryOptional.isEmpty()) throw new CustomException(String.format("Category: %d not found.",
                request.getCategoryId()), HttpStatus.NOT_FOUND);
        var category = categoryOptional.get();
        blog.setCategory(category);
        var file = fileUtil.upload(request.getFile());
        var fileEntity = new FileEntity();
        fileEntity.setFilePath(file);
        blog.setFileEntity(fileEntity);
        var savedBlog = blogRepository.save(blog);
        return savedBlog.getId();
    }

    @Override
    public Long update(Long id, UpdateBlogRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {
        var blog = blogRepository.findById(id);
        if (blog.isEmpty()) throw new CustomException(String.format("Blog: %d not found.",
                id), HttpStatus.NOT_FOUND);
        blogRepository.delete(blog.get());
    }

    @Override
    public List<BlogDTO> getAll() {
        var blogs = blogRepository.findAll();
        return blogMapper.toBlogDTOList(blogs);
    }

    @Override
    public BlogDTO getById(Long id) {
        var blog = blogRepository.findById(id);
        if (blog.isEmpty()) throw new CustomException(String.format("Blog: %d not found.",
                id), HttpStatus.NOT_FOUND);
        return blogMapper.toBlogDTO(blog.get());
    }
}
