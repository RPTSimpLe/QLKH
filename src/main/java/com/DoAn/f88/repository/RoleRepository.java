package com.DoAn.f88.repository;

import com.DoAn.f88.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("select r from RoleEntity r where r.code = :code")
    Optional<RoleEntity> findByCode(@Param("code") String code);
}
