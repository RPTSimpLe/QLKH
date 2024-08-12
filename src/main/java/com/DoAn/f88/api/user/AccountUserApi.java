package com.DoAn.f88.api.user;

import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/account")
public class AccountUserApi {
    @Autowired
    private AccountService  accountService;

    @GetMapping("/getAccount")
    public AccountDTO getAccount(){
        return accountService.getAccount();
    }
}
