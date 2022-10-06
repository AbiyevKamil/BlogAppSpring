package com.kamilabiyev.blog.repository;


import com.kamilabiyev.blog.model.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
