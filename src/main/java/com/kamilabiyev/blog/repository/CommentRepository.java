package com.kamilabiyev.blog.repository;

import com.kamilabiyev.blog.model.dto.CommentDTO;
import com.kamilabiyev.blog.model.entity.Blog;
import com.kamilabiyev.blog.model.entity.Comment;
import com.kamilabiyev.blog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<CommentDTO> findAllByUser(User user);
    List<CommentDTO> findAllByBlog(Blog blog);
}
