package com.example.demo.cors.common.base;

import com.example.demo.infrastructure.contant.PaginationContant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PageableRequest {

    private int page = PaginationContant.DEFAULT_PAGE;
    private int size = PaginationContant.DEFAULT_SIZE;

}
