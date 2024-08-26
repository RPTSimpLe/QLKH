package com.DoAn.f88.repository;

import com.DoAn.f88.entity.RoadMapCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RoadMapCourseRepository extends JpaRepository<RoadMapCourseEntity,Long> {
    @Query("select r from RoadMapCourseEntity r where r.roadMap.id = :roadMapId")
    List<RoadMapCourseEntity> findByRoadMapId(@PathVariable("roadMapId") Long roadMapId);
}
