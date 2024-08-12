package com.DoAn.f88.exeption.Error401.account;

import com.DoAn.f88.exeption.Error401.AuthException;
import com.DoAn.f88.request.account.CreateAccform;
import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.repository.AccountRepository;
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
            throw new AuthException("Tên đăng nhập đã tồn tại");
        }
        if (createAccform.getPassword().length() <= 8){
            throw new AuthException("Mật khẩu phải chứa ít nhất 8 kí tự");
        }
        Optional<AccountEntity> optionalAccountByEmail = accountRepository.findByEmail(createAccform.getEmail());
        if (optionalAccountByEmail.isPresent()) {
            throw new AuthException("Email đã tồn tại");
        }
        if (createAccform.getPhoneNumber().length() != 10){
            throw new AuthException("Số điện thoại phải dài 10 số");
        }
        Optional<AccountEntity> optionalAccountEntityByPhone = accountRepository.findByPhone(createAccform.getPhoneNumber());
        if (optionalAccountEntityByPhone.isPresent()){
            throw new AuthException("Mỗi số điện thoại chỉ được đăng kí 1 lần");

        }
    }
}
