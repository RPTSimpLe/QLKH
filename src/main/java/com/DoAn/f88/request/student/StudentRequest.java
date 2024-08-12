package com.DoAn.f88.request.student;

import com.DoAn.f88.request.account.AccountRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest extends AccountRequest {
    private Integer education;
}
