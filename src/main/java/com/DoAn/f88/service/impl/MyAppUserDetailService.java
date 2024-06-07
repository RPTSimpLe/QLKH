package com.DoAn.f88.service.impl;

import com.DoAn.f88.entity.RoleEntity;
import com.DoAn.f88.entity.UserEntity;
import com.DoAn.f88.exeption.ApplicationException;
import com.DoAn.f88.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MyAppUserDetailService implements UserDetailsService{
	
		@Autowired
		private UserRepository repository;
	
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Optional<UserEntity> userOtp = repository.findByUserName(username);
			if(userOtp.isEmpty()) {
				throw new ApplicationException("user not found");
			}
			UserEntity entity = userOtp.get();
			
			// user entity -> user detail của spring
			List<GrantedAuthority> roles = new ArrayList<>();
			
			List<RoleEntity> roleEntities = entity.getRoles();
			for (RoleEntity roleEntity : roleEntities) {
				GrantedAuthority authority = new SimpleGrantedAuthority(roleEntity.getCode());
				roles.add(authority);
			}
			
			UserDetails userDetail = User
					.withUsername(entity.getUsername())
					.password(entity.getPassword())
					//roles.(null)
					.authorities(roles)
					.build();
			return userDetail;
		}
}
