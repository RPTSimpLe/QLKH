package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.AccountConvert;
import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.dto.account.CreateAccform;
import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.entity.RoleEntity;
import com.DoAn.f88.exeption.Error401.ValidateAccountForm;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.exeption.Error403.ValidateValueForm;
import com.DoAn.f88.repository.AccountRepository;
import com.DoAn.f88.repository.RoleRepository;
import com.DoAn.f88.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
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
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
        public AccountDTO createAccount(CreateAccform createAccform) {
            ValidateValueForm.validateNull(createAccform);
            validateAccountForm.checkCreateAccform(createAccform);

            AccountEntity accountEntity = accountConvert.toEntity(createAccform);
            RoleEntity roleEntity = roleRepository.findByCode("USER").orElseThrow(() -> new ValidateException("Không tìm thấy quyền"));

            Long id = jdbcTemplate.queryForObject("select max(id) from accounts", Long.class) +1;
            accountEntity.setId(id);
            accountEntity.setCreateBy(null);
            accountEntity.setCreateDate(null);
            accountEntity.setModifierBy(null);
            accountEntity.setModifierDate(null);
            accountEntity.setDeleted(0);
            accountEntity.getRoles().add(roleEntity);
            roleEntity.getAccounts().add(accountEntity);

            String sqlInsertAcc = "INSERT INTO accounts (id,username,password,email,phoneNumber,name,gender,birthday,createDate,modifierDate,createBy,modifierBy,deleted) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            jdbcTemplate.update(sqlInsertAcc, accountEntity.getId(), accountEntity.getUsername(),accountEntity.getPassword(),accountEntity.getEmail(),
                accountEntity.getPhoneNumber(),accountEntity.getName(),accountEntity.getGender(),accountEntity.getBirthday(),
                accountEntity.getCreateDate(),accountEntity.getModifierDate(),accountEntity.getCreateBy(),accountEntity.getModifierBy(),
                accountEntity.getDeleted());

            roleRepository.save(roleEntity);
            return accountConvert.toDTO(accountEntity);
    }

    public void checkCreateAccform(CreateAccform createAccform){
        Optional<AccountEntity> optionalAccount = accountRepository.findByUserName(createAccform.getUsername());
        if (optionalAccount.isPresent()) {
            throw new ValidateException("Tên đăng nhập đã tồn tại");
        }
        if (createAccform.getPassword().length() <= 8){
            throw new ValidateException("Mật khẩu phải chứa ít nhất 8 kí tự");
        }
        Optional<AccountEntity> optionalAccountByEmail = accountRepository.findByEmail(createAccform.getEmail());
        if (optionalAccountByEmail.isPresent()) {
            throw new ValidateException("Email đã tồn tại");
        }
        if (createAccform.getPhoneNumber().length() != 10){
            throw new ValidateException("Số điện thoại phải dài 10 số");
        }

    }

    public <T> void validateEntity(T entity) {
        if (entity == null) {
            throw new ValidateException("Vui lòng nhập đầy đủ các trường");
        }

        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(entity);
                if (fieldValue == null || (fieldValue instanceof String && ((String) fieldValue).trim().isEmpty())) {
                    throw new ValidateException("Trường " + field.getName() + " không được để trống");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
