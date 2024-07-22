package com.DoAn.f88.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	
	@CreatedDate
	private Date createDate;
	@LastModifiedDate
	private Date modifierDate;
	@CreatedBy
	private String createBy;
	@LastModifiedBy
	private String modifierBy;
	@Column(nullable = false)
	@ColumnDefault("0")
	private Boolean deleted = false;
}
