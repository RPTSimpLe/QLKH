package com.DoAn.f88.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword {
    public String oldPassword;
    public String newPassword;
    public String confirmPassword;
}
