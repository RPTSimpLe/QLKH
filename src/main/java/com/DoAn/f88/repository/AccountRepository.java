package com.DoAn.f88.repository;

import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

	@Query("select r from AccountEntity  r where  r.isDeleted = false and r.id = :id")
	Optional<AccountEntity> findById(@Param("id") Long id);

	@Query("SELECT r FROM AccountEntity r WHERE r.isDeleted = false and r.username = :username  ")
	Optional<AccountEntity> findByUserName(@Param(value = "username") String username);

	@Query("SELECT r FROM AccountEntity r WHERE r.isDeleted = false and r.email = :email")
	Optional<AccountEntity> findByEmail(@Param(value = "email") String email);

	@Query("SELECT r FROM AccountEntity r WHERE r.isDeleted = false and r.phoneNumber = :phoneNumber")
	Optional<AccountEntity> findByPhone(String phoneNumber);

	@Query("SELECT r from AccountEntity r where r.isDeleted = false and r.codeAccount = :codeAccount")
	Optional<AccountEntity> findByCodeAccount(@Param(value = "codeAccount") String codeAccount);
}
