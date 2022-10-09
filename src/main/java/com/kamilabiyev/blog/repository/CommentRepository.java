package com.kamilabiyev.blog.repository;

import com.kamilabiyev.blog.model.dto.CommentDTO;
import com.kamilabiyev.blog.model.entity.Blog;
import com.kamilabiyev.blog.model.entity.Comment;
import com.kamilabiyev.blog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment as c WHERE c.user = ?1")
    List<CommentDTO> findAllByUser(User user);
    @Query("SELECT c FROM Comment as c WHERE c.blog = ?1")
    List<CommentDTO> findAllByBlog(Blog blog);
}
