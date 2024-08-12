package com.DoAn.f88.service;

import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.request.account.CreateAccform;
import com.DoAn.f88.request.account.PutUniqueAttributeAccountRequest;
import com.DoAn.f88.request.student.StudentCreateForm;
import com.DoAn.f88.request.student.StudentRequest;
import com.DoAn.f88.request.employee.EmployeeCreateForm;
import com.DoAn.f88.request.employee.EmployeeRequest;

import java.util.Map;

public interface AccountService {
    AccountEntity createAccount(CreateAccform createAccform);
    AccountDTO createEmployeeAccount(EmployeeCreateForm form);
    AccountDTO createAdminAccount(CreateAccform form);
    AccountDTO createStudentAccount(StudentCreateForm studentform);

    AccountDTO findAccountById(Long id);
    PageDTO<AccountDTO> getAccount(Map<String,String> params);
    AccountDTO putStudentAccount(StudentRequest form, String id);
    AccountDTO putEmployeeAccount(EmployeeRequest form, String id);

    void deleteAccount(String id);

    AccountDTO updateEmail(PutUniqueAttributeAccountRequest request);
    AccountDTO updatePhone(PutUniqueAttributeAccountRequest request);
    AccountDTO resetPassword(PutUniqueAttributeAccountRequest request);

    AccountDTO getAccount();
}
