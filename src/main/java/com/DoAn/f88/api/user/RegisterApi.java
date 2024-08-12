package com.DoAn.f88.api.user;

import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.request.student.StudentCreateForm;
import com.DoAn.f88.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterApi {
    @Autowired
    private AccountService accountService;

    @PostMapping()
    public AccountDTO register(@RequestBody StudentCreateForm createAccform) {
        return accountService.createStudentAccount(createAccform);
    }
}
