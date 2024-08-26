package com.DoAn.f88.repository;

import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.entity.RoadMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoadMapRepository extends JpaRepository<RoadMapEntity,Long> {
    @Query("select r from RoadMapEntity r where r.isDeleted = false and r.id = :id")
    Optional<RoadMapEntity> findById(@Param("id") Long id);
}
