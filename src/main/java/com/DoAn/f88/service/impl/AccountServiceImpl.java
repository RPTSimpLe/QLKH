package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.AccountConvert;
import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.dto.account.CreateAccform;
import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.entity.RoleEntity;
import com.DoAn.f88.exeption.ValidateAccountForm;
import com.DoAn.f88.exeption.ValidateException;
import com.DoAn.f88.exeption.ValidateValueForm;
import com.DoAn.f88.repository.AccountRepository;
import com.DoAn.f88.repository.RoleRepository;
import com.DoAn.f88.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountConvert accountConvert;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ValidateAccountForm validateAccountForm;

    @Override
    public AccountDTO createAccount(CreateAccform createAccform) {
        ValidateValueForm.validateEntity(createAccform);
        validateAccountForm.checkCreateAccform(createAccform);

        AccountEntity accountEntity = accountConvert.toEntity(createAccform);
        RoleEntity roleEntity = roleRepository.findByCode("USER").orElseThrow(() -> new ValidateException("Không tìm thấy tài khoản"));
        accountEntity.setDeleted(0);
        accountEntity.getRoles().add(roleEntity);
        accountRepository.save(accountEntity);

        roleEntity.getAccounts().add(accountEntity);
        roleRepository.save(roleEntity);
        return accountConvert.toDTO(accountEntity);
    }
}
