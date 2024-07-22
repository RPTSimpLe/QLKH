package com.DoAn.f88.repository;

import com.DoAn.f88.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<TeacherEntity,Long> {
//    @Query("SELECT T")
}
