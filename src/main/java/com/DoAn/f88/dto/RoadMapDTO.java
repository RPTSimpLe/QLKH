package com.DoAn.f88.dto;

import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.entity.StudentEntity;
import jakarta.persistence.*;

public class RoadMapDTO extends BaseDTO {
    private Long id;

    private String code;
    private String name;
    private String discount;
    private String description;
}
