package com.kamilabiyev.blog.repository;

import com.kamilabiyev.blog.model.entity.Blog;
import com.kamilabiyev.blog.model.entity.Category;
import com.kamilabiyev.blog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByCategory(Category category);
    Optional<Blog> findByUser(User user);
}
