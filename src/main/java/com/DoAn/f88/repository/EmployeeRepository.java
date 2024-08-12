package com.DoAn.f88.repository;

import com.DoAn.f88.entity.EmployeeEntity;
import com.DoAn.f88.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    @Query("select r from EmployeeEntity  r where  r.isDeleted = false and r.id = :id")
    Optional<EmployeeEntity> findById(@Param("id") Long id);
}
