package com.DoAn.f88.request.employee;

import com.DoAn.f88.request.account.CreateAccform;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeCreateForm extends CreateAccform {
    private String certificate;
}
