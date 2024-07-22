package com.DoAn.f88.api.admin;

import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.formCreate.CreateAccform;
import com.DoAn.f88.formCreate.StudentCreateForm;
import com.DoAn.f88.formCreate.TeacherCreateForm;
import com.DoAn.f88.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/api/v1/account")
public class AccountAdminApi {
    @Autowired
    private AccountService accountService;

    @PostMapping("/createTeacherAcc")
    public AccountDTO createTeacherAcc(@RequestBody TeacherCreateForm createForm) {
        return accountService.createTeacherAccount(createForm);
    }
    @PostMapping("/createAdminAcc")
    public AccountDTO createAdminAcc(@RequestBody CreateAccform createForm) {
        return accountService.createAdminAccount(createForm);
    }
    @PostMapping("/createStudentAcc")
    public AccountDTO createStudentAcc(@RequestBody StudentCreateForm createForm) {
        return accountService.createStudentAccount(createForm);
    }
//    @PostMapping("/createEmployeeAcc")
//    public AccountDTO createEmployeeAcc(@RequestBody EmployeeCreateForm createForm) {
//        return accountService.createEmployeeAccount(createForm);
//    }
}
