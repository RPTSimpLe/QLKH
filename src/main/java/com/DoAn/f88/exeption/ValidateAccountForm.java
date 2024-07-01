package com.DoAn.f88.exeption;

import com.DoAn.f88.dto.account.CreateAccform;
import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.repository.AccountRepository;
import com.DoAn.f88.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidateAccountForm {
    @Autowired
    private AccountRepository accountRepository;

    public void checkCreateAccform(CreateAccform createAccform){
        Optional<AccountEntity> optionalAccount = accountRepository.findByUserName(createAccform.getUsername());
        if (optionalAccount.isPresent()) {
            throw new ValidateException("Tên đăng nhập đã tồn tại");
        }
        Optional<AccountEntity> optionalAccountByEmail = accountRepository.findByEmail(createAccform.getEmail());
        if (optionalAccountByEmail.isPresent()) {
            throw new ValidateException("Email đã tồn tại");
        }
        if (createAccform.getPhoneNumber().length() != 10){
            throw new ValidateException("Số điện thoại phải dài 10 số");
        }
        if (createAccform.getPassword().length() <= 4){
            throw new ValidateException("Mật khẩu phải chứa ít nhất 8 kí tự");
        }

    }
}
