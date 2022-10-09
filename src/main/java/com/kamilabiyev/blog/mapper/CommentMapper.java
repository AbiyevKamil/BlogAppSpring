package com.kamilabiyev.blog.mapper;

import com.kamilabiyev.blog.model.dto.CommentDTO;
import com.kamilabiyev.blog.model.entity.Comment;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentMapper {
    UserMapper userMapper;

    public CommentDTO toCommentDTO(Comment comment) {

        return new CommentDTO(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                userMapper.toUserDTO(comment.getUser())
        );
    }

    public List<CommentDTO> toCommentDTOList(List<Comment> comments) {
        return comments.stream().map(this::toCommentDTO).collect(Collectors.toList());
    }
}
