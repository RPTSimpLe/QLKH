package com.DoAn.f88.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roadMaps")
public class RoadMapEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String discount;
    private String description;
    @OneToMany(mappedBy = "roadMap")
    private List<RoadMapCourseEntity> roadMapCourses = new ArrayList<>();
}
