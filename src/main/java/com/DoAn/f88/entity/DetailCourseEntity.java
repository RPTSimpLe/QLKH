package com.DoAn.f88.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detailCourses")
public class DetailCourseEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namePreiod;
    private Long numberPreiod;
    private Integer duration;
    private String recordUrl;
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
}
