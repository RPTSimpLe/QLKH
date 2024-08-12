package com.DoAn.f88.service;

import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.entity.RoleEntity;
import com.DoAn.f88.entity.RolesAccountsEntity;
import org.springframework.stereotype.Service;

public interface RolesAccountService {
    RolesAccountsEntity create (RoleEntity roleEntity, AccountEntity accountEntity);
    RolesAccountsEntity update (RoleEntity roleEntity, AccountEntity accountEntity);
}
