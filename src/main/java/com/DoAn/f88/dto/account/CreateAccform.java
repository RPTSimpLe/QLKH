package com.DoAn.f88.dto.account;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateAccform {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private String gender;
    private String birthday;
}
