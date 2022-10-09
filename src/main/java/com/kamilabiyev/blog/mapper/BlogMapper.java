package com.kamilabiyev.blog.mapper;

import com.kamilabiyev.blog.model.dto.BlogDTO;
import com.kamilabiyev.blog.model.entity.Blog;
import com.kamilabiyev.blog.model.request.blog.AddBlogRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogMapper {
    UserMapper userMapper;
    CategoryMapper categoryMapper;
    CommentMapper commentMapper;

    public BlogDTO toBlogDTO(Blog blog) {
        return new BlogDTO(
                blog.getId(),
                blog.getTitle(),
                blog.getContent(),
                blog.getCreatedAt(),
                blog.getUpdatedAt(),
                userMapper.toUserDTO(blog.getUser()),
                categoryMapper.toCategoryDTO(blog.getCategory()),
                commentMapper.toCommentDTOList(new ArrayList<>(blog.getComments()))
        );
    }

    public List<BlogDTO> toBlogDTOList(List<Blog> blogs) {
        return blogs.stream().map(this::toBlogDTO).collect(Collectors.toList());
    }

    public Blog toBlog(AddBlogRequest blog) {
        return new Blog(
                blog.getTitle(),
                blog.getContent()
        );
    }
}
