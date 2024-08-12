package com.DoAn.f88.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO extends BaseDTO {
	private Long id;
	
	private String code;
	
	private String name;
	private Long parentId;
	
}
