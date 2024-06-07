package com.DoAn.f88.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserform {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String password;
    private String roleId;
}
