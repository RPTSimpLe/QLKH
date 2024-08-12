package com.DoAn.f88.dto.account;

import com.DoAn.f88.dto.BaseDTO;
import com.DoAn.f88.entity.ImageEntity;
import com.DoAn.f88.entity.RoleEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class AccountDTO <T> extends BaseDTO {
	
	private Long id;

	private String username;
	private String email;
	private String phoneNumber;
	private String name;
	private Integer gender;
	private String birthday;
	private String codeAccount;
	private T extendAttribute;
	private Long imageId;
	private String url;
	
	private List<String> roleName = new ArrayList<String>();
	private List<String> roleCode = new ArrayList<>();
	private List<ImageEntity> imageEntities = new ArrayList<>();

}
