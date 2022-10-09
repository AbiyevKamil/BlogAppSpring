package com.kamilabiyev.blog.model.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class AddCategoryRequest {
    private String name;
}
