package com.DoAn.f88.repository;

import com.DoAn.f88.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	
	@Query("SELECT r FROM AccountEntity r WHERE r.username = :username")
	Optional<AccountEntity> findByUserName(@Param(value ="username") String username);

	@Query("SELECT r FROM AccountEntity r WHERE r.email = :email")
	Optional<AccountEntity> findByEmail(@Param(value ="email") String email);

	@Query("SELECT r FROM AccountEntity r WHERE r.phoneNumber = :phoneNumber")
	Optional<AccountEntity> findByPhone(String phoneNumber);
}
