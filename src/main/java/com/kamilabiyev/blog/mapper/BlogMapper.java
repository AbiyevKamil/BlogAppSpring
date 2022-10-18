package com.kamilabiyev.blog.mapper;

import com.kamilabiyev.blog.model.dto.BlogDTO;
import com.kamilabiyev.blog.model.entity.Blog;
import com.kamilabiyev.blog.model.request.blog.AddBlogRequest;
import com.kamilabiyev.blog.properties.FileProperties;
import com.kamilabiyev.blog.utils.EnvUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogMapper {
    UserMapper userMapper;
    CategoryMapper categoryMapper;
    CommentMapper commentMapper;
    FileProperties fileProperties;
    EnvUtil envUtil;

    public BlogDTO toBlogDTO(Blog blog) {
        return new BlogDTO(
                blog.getId(),
                blog.getTitle(),
                blog.getContent(),
                blog.getCreatedAt(),
                blog.getUpdatedAt(),
                fileProperties.getUploadFolder() + blog.getFileEntity().getFilePath(),
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
