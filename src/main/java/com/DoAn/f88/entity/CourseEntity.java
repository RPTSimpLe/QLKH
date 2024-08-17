package com.DoAn.f88.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Courses")
public class CourseEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;
    private Long price;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<DetailCourseEntity> detailCourse = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<TimekeepingEntity> timekeepingEntity = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<RoadMapCourseEntity> roadMapCourse = new ArrayList<>();
}
