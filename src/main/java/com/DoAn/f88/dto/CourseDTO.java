package com.DoAn.f88.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseDTO extends BaseDTO {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Integer duration;
    private Integer numberPreiod;
    private String level;
    private Long price;
}
