package com.DoAn.f88.repository;

import com.DoAn.f88.entity.DetailCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailCourseRepository extends JpaRepository<DetailCourseEntity,Long> {
    @Query("select r from DetailCourseEntity r where r.isDeleted = false and r.course.id = :course_id ")
    List<DetailCourseEntity> findAll(@Param("course_id") Long course_id);
}
