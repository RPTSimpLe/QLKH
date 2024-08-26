package com.DoAn.f88.repository;

import com.DoAn.f88.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
    @Query("select r from CourseEntity r where r.isDeleted = false and r.id = :id")
    Optional<CourseEntity> findById(@Param("id") Long id);

    @Query("select r from CourseEntity r where r.isDeleted = false")
    List<CourseEntity> findAll();

    @Query("select r.price from CourseEntity r where r.isDeleted = false and r.id = :id")
    Long getPriceById(@Param("id") Long id);
}
