package com.DoAn.f88.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseRequest {
    private String name;
    private String description;
    private Integer numberPreiod;
    private Integer price;
    private Integer duration;
    private String code;
}
