package com.DoAn.f88.repository;

import com.DoAn.f88.entity.DetailCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailCourseRepository extends JpaRepository<DetailCourseEntity,Long> {
    @Query("select r from CourseEntity r where r.isDeleted = false ")
    List<DetailCourseEntity> findAll();
}
