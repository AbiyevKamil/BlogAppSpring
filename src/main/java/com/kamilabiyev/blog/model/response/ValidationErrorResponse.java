package com.kamilabiyev.blog.model.response;

import lombok.Data;

@Data
public class ValidationErrorResponse {
    private String field;
    private Object value;
    private String message;
}
