package com.DoAn.f88.repository;

import com.DoAn.f88.entity.ImageEntity;
import com.DoAn.f88.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("select r from RoleEntity  r where  r.isDeleted = false and r.id = :id")
    Optional<RoleEntity> findById(@Param("id") Long id);

    @Query("select r from RoleEntity r where r.isDeleted = false and r.code = :code")
    Optional<RoleEntity> findByCode(@Param("code") String code);

    Optional<RoleEntity> findByName(String name);

    @Query("select r from RoleEntity r where r.isDeleted = false and r.code = :roleCode")
    Optional<RoleEntity> findByRoleCode(@Param("roleCode") Long roleCode);

    @Query("select r from RoleEntity r where r.isDeleted = false and r.parentId = :parentId")
    List<RoleEntity> findByParentId(@Param("parentId") Long parentId);
}
