package com.DoAn.f88.dto;

import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.entity.RoadMapEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoadMapCourseDTO {
    private Long id;
    private Long courseId;
    private Long roadMapId;
}
