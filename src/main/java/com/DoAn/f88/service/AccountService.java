package com.DoAn.f88.service;

import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.formCreate.CreateAccform;
import com.DoAn.f88.formCreate.StudentCreateForm;
import com.DoAn.f88.formCreate.TeacherCreateForm;

public interface AccountService {
    AccountEntity createAccount(CreateAccform createAccform);
    AccountDTO createTeacherAccount(TeacherCreateForm form);
    AccountDTO createAdminAccount(CreateAccform form);
    AccountDTO createStudentAccount(StudentCreateForm studentform);
//    AccountDTO createEmployeeAccount(EmployeeCreateForm employeeform);

    AccountDTO getAccount(String username, String email, String phoneNumber, String name, String birthday);
}
