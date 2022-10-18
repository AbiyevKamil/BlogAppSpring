package com.kamilabiyev.blog.service.impl;

import com.kamilabiyev.blog.exception.CustomException;
import com.kamilabiyev.blog.mapper.BlogMapper;
import com.kamilabiyev.blog.model.dto.BlogDTO;
import com.kamilabiyev.blog.model.entity.Blog;
import com.kamilabiyev.blog.model.entity.FileEntity;
import com.kamilabiyev.blog.model.request.blog.AddBlogRequest;
import com.kamilabiyev.blog.model.request.blog.FilterBlogRequest;
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
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Locale;

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
    @Transactional
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
        var filePath = fileUtil.upload(request.getFile());
        var fileEntity = new FileEntity();
        fileEntity.setFilePath(filePath);
        blog.setFileEntity(fileEntity);
        var savedBlog = blogRepository.save(blog);
        return savedBlog.getId();
    }

    @Override
    @Transactional
    public Long update(Long id, UpdateBlogRequest request) {
        var exist = blogRepository.findById(id);
        if (exist.isEmpty()) throw new CustomException(String.format("Blog: %d not found.", id), HttpStatus.NOT_FOUND);
        var blog = exist.get();
        if (!request.getFile().isEmpty()) {
            var file = blog.getFileEntity();
            fileUtil.delete(file.getFilePath());
            fileRepository.deleteById(file.getId());
            var newFile = new FileEntity();
            newFile.setFilePath(fileUtil.upload(request.getFile()));
            blog.setFileEntity(newFile);
        }

        if (!blog.getCategory().getId().equals(request.getCategoryId())) {
            var existCategory = categoryRepository.findById(request.getCategoryId());
            if (exist.isEmpty())
                throw new CustomException(String.format("Category: %d not found.", id), HttpStatus.NOT_FOUND);
            var category = existCategory.get();
            blog.setCategory(category);
        }

        blog.setTitle(request.getTitle());
        blog.setContent(request.getContent());
        blogRepository.save(blog);
        return blog.getId();
    }

    @Override
    public void delete(Long id) {
        var blog = blogRepository.findById(id);
        if (blog.isEmpty()) throw new CustomException(String.format("Blog: %d not found.",
                id), HttpStatus.NOT_FOUND);
        blogRepository.delete(blog.get());
    }

    @Override
    public List<BlogDTO> getAll(FilterBlogRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPageNumber(),
                request.getPageSize());
        if (!request.getSortField().isBlank()) {
            pageRequest = PageRequest.of(request.getPageNumber(),
                    request.getPageSize(),
                    Sort.by(request.getIsAsc() ? Sort.Direction.ASC :
                            Sort.Direction.DESC, request.getSortField()));
        }

        Specification<Blog> specification = (root, query, criteriaBuilder) -> {
            var categoryFilter = criteriaBuilder.equal(root.get("category").get("id"),
                    request.getCategoryId());
            var titleFilter = criteriaBuilder.like(criteriaBuilder
                    .upper(root.get("title")), "%" + request.getTitle().toUpperCase(Locale.ROOT) + "%");
            var contentFilter = criteriaBuilder.like(criteriaBuilder
                    .upper(root.get("content")), "%" + request.getContent().toUpperCase(Locale.ROOT) + "%");
            return criteriaBuilder.and(categoryFilter, titleFilter, contentFilter);
        };
        var blogs = blogRepository.findAll(specification, pageRequest);
        return blogMapper.toBlogDTOList(blogs.getContent());
    }

    @Override
    public BlogDTO getById(Long id) {
        var blog = blogRepository.findById(id);
        if (blog.isEmpty()) throw new CustomException(String.format("Blog: %d not found.",
                id), HttpStatus.NOT_FOUND);
        return blogMapper.toBlogDTO(blog.get());
    }
}
