package com.kamilabiyev.blog.model.request.base;

import lombok.Data;

@Data
public class BaseSortingRequest {
    private String sortField;
    private Boolean isAsc = Boolean.TRUE;
}
