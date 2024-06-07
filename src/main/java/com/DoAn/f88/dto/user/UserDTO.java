package com.DoAn.f88.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.DoAn.f88.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDTO {
	
private Long userId;
	
	private String username;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date createdDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date modifierDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String modifierBy;
	
	private List<UserEntity> role;
	private List<String> roleName = new ArrayList<String>();
	
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", address=" + address + ", createdDate=" + createdDate
				+ ", modifierDate=" + modifierDate + "]";
	}
	
}	
