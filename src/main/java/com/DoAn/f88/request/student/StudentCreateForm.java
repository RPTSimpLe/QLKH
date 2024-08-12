package com.DoAn.f88.request.student;

import com.DoAn.f88.request.account.CreateAccform;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentCreateForm extends CreateAccform {
    private Integer education;
}
