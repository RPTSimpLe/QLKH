package com.DoAn.f88.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageDTO<T> {
    private Integer page;
    private Integer limit;
    private Long totalItems;
    private List<T> data = new ArrayList<T>();

    public PageDTO(Integer page, Integer limit, Long totalItems, List<T> data) {
        this.page = page;
        this.limit = limit;
        this.totalItems = totalItems;
        this.data = data;
    }
}
