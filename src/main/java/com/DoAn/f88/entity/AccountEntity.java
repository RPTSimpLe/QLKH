package com.DoAn.f88.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts	")
public class AccountEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String phoneNumber;

	private String name;

	private Integer gender;

	private String birthday;

	@Column(unique = true)
	private String codeAccount;

	@OneToOne(mappedBy = "account")
	private StudentEntity studentEntity;

	@OneToOne(mappedBy = "account")
	private EmployeeEntity employeeEntity;

	@OneToMany(mappedBy = "account")
	private List<RolesAccountsEntity> rolesAccountsEntities = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "image_id", referencedColumnName = "id")
	private ImageEntity imageEntity;
}
