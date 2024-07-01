package com.DoAn.f88.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
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
	private Integer deleted;

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifierDate() {
		return modifierDate;
	}
	public void setModifierDate(Date modifierDate) {
		this.modifierDate = modifierDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getModifierBy() {
		return modifierBy;
	}
	public void setModifierBy(String modifierBy) {
		this.modifierBy = modifierBy;
	}
	
	
}
