package com.kamilabiyev.blog.model.request.base;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class BasePagingRequest extends BaseSortingRequest {
    private Integer pageNumber = 0;
    @Min(1)
    private Integer pageSize = 10;
}
