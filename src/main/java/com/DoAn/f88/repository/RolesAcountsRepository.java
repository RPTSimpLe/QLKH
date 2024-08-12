package com.DoAn.f88.repository;

import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.entity.ImageEntity;
import com.DoAn.f88.entity.RoleEntity;
import com.DoAn.f88.entity.RolesAccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RolesAcountsRepository extends JpaRepository<RolesAccountsEntity,Long> {
    @Query("select r from RolesAccountsEntity  r where  r.isDeleted = false and r.id = :id")
    Optional<RolesAccountsEntity> findById(@Param("id") Long id);

    @Query("SELECT r from RolesAccountsEntity r where r.isDeleted = false and r.account.id = :accountId")
    List<RolesAccountsEntity> findByAccount(@Param("accountId") Long accountId);

    @Query("SELECT r from RolesAccountsEntity r where r.isDeleted = false and r.role.id = :roleId")
    List<RolesAccountsEntity> findByRole(@Param("roleId") String roleId);

    @Query("SELECT r from RolesAccountsEntity r where r.isDeleted = false and r.role = :role and r.account = :account")
    Optional<RolesAccountsEntity> findByRoleAAndAccount(@Param("role") RoleEntity role, @Param("account") AccountEntity account);
}
