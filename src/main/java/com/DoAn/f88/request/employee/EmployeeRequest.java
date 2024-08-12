package com.DoAn.f88.request.employee;

import com.DoAn.f88.request.account.AccountRequest;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeRequest extends AccountRequest {
    private String certificate;
}
