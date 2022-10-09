package com.kamilabiyev.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String Content;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS", timezone = "Asia/Baku")
    private Timestamp createdAt;
    private UserDTO user;
}
