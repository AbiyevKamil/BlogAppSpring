package com.kamilabiyev.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class BlogDTO {
    private Long id;
    private String title;
    private String content;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS", timezone = "Asia/Baku")
    private Timestamp createdAt;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS", timezone = "Asia/Baku")
    private Timestamp updatedAt;
    private UserDTO user;
    private CategoryDTO category;
    private List<CommentDTO> comments;
}
