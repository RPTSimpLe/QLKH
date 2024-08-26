package com.DoAn.f88.dto;

import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.entity.StudentEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoadMapDTO extends BaseDTO {
    private Long id;

    private String code;
    private String name;
    private String discount;
    private String description;
    private List<String> nameCourse = new ArrayList<String>();
    private Long totalPreiod;
    private Long totalPrice;
}
