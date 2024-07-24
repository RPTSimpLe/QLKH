package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.AccountConvert;
import com.DoAn.f88.convert.StudentConvert;
import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.entity.StudentEntity;
import com.DoAn.f88.entity.TeacherEntity;
import com.DoAn.f88.formCreate.CreateAccform;
import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.entity.RoleEntity;
import com.DoAn.f88.exeption.Error401.ValidateAccountForm;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.exeption.Error403.ValidateValueForm;
import com.DoAn.f88.formCreate.StudentCreateForm;
import com.DoAn.f88.formCreate.TeacherCreateForm;
import com.DoAn.f88.repository.AccountRepository;
import com.DoAn.f88.repository.RoleRepository;
import com.DoAn.f88.repository.StudentRepository;
import com.DoAn.f88.repository.TeacherRepository;
import com.DoAn.f88.service.AccountService;
import com.DoAn.f88.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public AccountEntity createAccount(CreateAccform createAccform) {
        String role = createAccform.getRole().substring(0,1);
        LocalDateTime dateTimeStamp = LocalDateTime.now();
        String year = DateTimeFormatter.ofPattern("YYYY").format(dateTimeStamp);
        String milisecond = String.valueOf(System.currentTimeMillis());
        String code = role+year+milisecond;

        AccountEntity accountEntity = accountConvert.toEntity(createAccform);
        accountEntity.setCodeAccount(code);
        return accountEntity;
    }

    @Override
    public AccountDTO createTeacherAccount(TeacherCreateForm form) {
        ValidateValueForm.validateNull(form);
        validateAccountForm.checkCreateAccform(form);
        AccountEntity accountEntity = createAccount(form);

        RoleEntity roleEntity = roleRepository.findByCode(form.getRole()).orElseThrow(() -> new ValidateException("Không tìm thấy quyền"));
        roleEntity.getAccounts().add(accountEntity);
        accountRepository.save(accountEntity);
        roleRepository.save(roleEntity);

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setCertificate(form.getCertificate());
        teacherEntity.setAccount(accountEntity);
        teacherRepository.save(teacherEntity);
        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public AccountDTO createAdminAccount(CreateAccform form) {
        ValidateValueForm.validateNull(form);
        validateAccountForm.checkCreateAccform(form);
        AccountEntity accountEntity = createAccount(form);

        RoleEntity roleEntity = roleRepository.findByCode(form.getRole()).orElseThrow(() -> new ValidateException("Không tìm thấy quyền"));
        roleEntity.getAccounts().add(accountEntity);
        accountRepository.save(accountEntity);
        roleRepository.save(roleEntity);
        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public AccountDTO createStudentAccount(StudentCreateForm studentform) {
        ValidateValueForm.validateNull(studentform);
        validateAccountForm.checkCreateAccform(studentform);
        AccountEntity accountEntity = createAccount(studentform);

        RoleEntity roleEntity = roleRepository.findByCode(studentform.getRole()).orElseThrow(() -> new ValidateException("Không tìm thấy quyền"));
        roleEntity.getAccounts().add(accountEntity);
        accountRepository.save(accountEntity);
        roleRepository.save(roleEntity);

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEducation(studentform.getEducation());
        studentEntity.setAccount(accountEntity);
        studentRepository.save(studentEntity);
        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public AccountDTO getAccount(String username, String email, String phoneNumber, String name, String birthday) {

        return null;
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
        Optional<AccountEntity> optionalAccountByPhone = accountRepository.findByPhone(createAccform.getPhoneNumber());
        if (optionalAccountByPhone.isPresent()) {
            throw new ValidateException("Mỗi số điện thoại chỉ được đăng kí 1 lần");
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
