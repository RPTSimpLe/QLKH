package com.DoAn.f88.api.admin;

import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.request.account.CreateAccform;
import com.DoAn.f88.request.account.PutUniqueAttributeAccountRequest;
import com.DoAn.f88.request.student.StudentCreateForm;
import com.DoAn.f88.request.student.StudentRequest;
import com.DoAn.f88.request.employee.EmployeeCreateForm;
import com.DoAn.f88.request.employee.EmployeeRequest;
import com.DoAn.f88.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("admin/api/v1/account")
public class AccountAdminApi {
    @Autowired
    private AccountService accountService;

    @PostMapping("createEmployeeAcc")
    public AccountDTO createEmployeeAcc(@RequestBody EmployeeCreateForm createForm) {
        return accountService.createEmployeeAccount(createForm);
    }
    @PostMapping("createAdminAcc")
    public AccountDTO createAdminAcc(@RequestBody CreateAccform createForm) {
        return accountService.createAdminAccount(createForm);
    }
    @PostMapping("createStudentAcc")
    public AccountDTO createStudentAcc(@RequestBody StudentCreateForm createForm) {
        return accountService.createStudentAccount(createForm);
    }
    @GetMapping("getAll")
    public PageDTO<AccountDTO> getAll(@RequestParam Map<String, String> params) {
        return accountService.getAccount(params);
    }
    @PutMapping("employee/{id}")
    public AccountDTO updateEmployee(@RequestBody EmployeeRequest request, @PathVariable String id) {
        return accountService.putEmployeeAccount(request,id);
    }
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable String id) {
        accountService.deleteAccount(id);
    }

    @PutMapping("/resetPassword")
    public AccountDTO resetPassword(@RequestBody PutUniqueAttributeAccountRequest request) {
        return accountService.resetPassword(request);
    }
}
