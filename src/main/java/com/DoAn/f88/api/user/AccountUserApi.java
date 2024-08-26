package com.DoAn.f88.api.user;

import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.request.account.PutUniqueAttributeAccountRequest;
import com.DoAn.f88.request.student.StudentRequest;
import com.DoAn.f88.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/api/v1/account")
public class AccountUserApi {
    @Autowired
    private AccountService  accountService;

    @PutMapping("/updateEmail")
    public AccountDTO updateEmail(@RequestBody PutUniqueAttributeAccountRequest request) {
        return accountService.updateEmail(request);
    }
    @PutMapping("/updatePhone")
    public AccountDTO updatePhone(@RequestBody PutUniqueAttributeAccountRequest request) {
        return accountService.updatePhone(request);
    }
    @GetMapping("/findById/{id}")
    public AccountDTO findById(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }
    @PutMapping("/student/{id}")
    public AccountDTO updateStudent(@RequestBody StudentRequest request, @PathVariable String id) {
        return accountService.putStudentAccount(request,id);
    }
}
