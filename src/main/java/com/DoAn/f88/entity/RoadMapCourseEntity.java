package com.DoAn.f88.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roadMap_course")
public class RoadMapCourseEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
    @ManyToOne
    @JoinColumn(name = "roadMap_id")
    private RoadMapEntity roadMap;
}
