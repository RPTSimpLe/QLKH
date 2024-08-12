package com.DoAn.f88.request.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class CreateAccform {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private Integer gender;
    private String birthday;
    private List<String> role = new ArrayList<>();
}
