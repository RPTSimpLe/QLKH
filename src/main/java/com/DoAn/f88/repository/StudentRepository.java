package com.DoAn.f88.repository;

import com.DoAn.f88.entity.ImageEntity;
import com.DoAn.f88.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    @Query("select r from StudentEntity  r where  r.isDeleted = false and r.id = :id")
    Optional<StudentEntity> findById(@Param("id") Long id);
}
