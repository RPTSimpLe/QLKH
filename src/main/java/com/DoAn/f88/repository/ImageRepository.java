package com.DoAn.f88.repository;

import com.DoAn.f88.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    @Query("select r from ImageEntity  r where  r.isDeleted = false and r.Id = :id")
    Optional<ImageEntity> findById(@Param("id") Long id);
}
