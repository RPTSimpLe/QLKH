package com.DoAn.f88.api.user;

import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.dto.account.CreateAccform;
import com.DoAn.f88.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterApi {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public AccountDTO register(@RequestBody CreateAccform createAccform) {
        return accountService.createAccount(createAccform);
    }
}
