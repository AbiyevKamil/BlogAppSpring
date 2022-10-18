package com.kamilabiyev.blog.model.request.blog;

import com.kamilabiyev.blog.model.request.base.BasePagingRequest;
import lombok.Data;

@Data
public class FilterBlogRequest extends BasePagingRequest {
    private String title = "";
    private String content = "";
    private Long categoryId;
}
