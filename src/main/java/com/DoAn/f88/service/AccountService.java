package com.DoAn.f88.service;

import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.dto.account.CreateAccform;

public interface AccountService {
    AccountDTO createAccount(CreateAccform createAccform);
}
