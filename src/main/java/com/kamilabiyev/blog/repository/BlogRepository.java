package com.kamilabiyev.blog.repository;

import com.kamilabiyev.blog.model.entity.Blog;
import com.kamilabiyev.blog.model.entity.Category;
import com.kamilabiyev.blog.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    List<Blog> findAllByCategory(Category category);
    Optional<Blog> findByUser(User user);
//    @Query("SELECT Blog from Blog where (category = ?2 or ?2 is null) and (title like '%?3%')")
//    Page<Blog> filterAll(Pageable pageable, Long categoryId, String title, String content);
}
