package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.AccountConvert;
import com.DoAn.f88.convert.StudentConvert;
import com.DoAn.f88.convert.EmployeeConvert;
import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.entity.*;
import com.DoAn.f88.exeption.Error401.AuthException;
import com.DoAn.f88.repository.*;
import com.DoAn.f88.request.account.AccountRequest;
import com.DoAn.f88.exeption.Error403.CheckNullVariable;
import com.DoAn.f88.request.account.CreateAccform;
import com.DoAn.f88.exeption.Error401.account.ValidateAccountForm;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.exeption.Error403.ValidateValueForm;
import com.DoAn.f88.request.account.PutUniqueAttributeAccountRequest;
import com.DoAn.f88.request.student.StudentCreateForm;
import com.DoAn.f88.request.student.StudentRequest;
import com.DoAn.f88.request.employee.EmployeeCreateForm;
import com.DoAn.f88.request.employee.EmployeeRequest;
import com.DoAn.f88.service.AccountService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private EmployeeRepository employeeRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private StudentConvert studentConvert;
    @Autowired
    private EmployeeConvert employeeConvert;
    @Autowired
    private RolesAcountsRepository rolesAcountsRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public AccountEntity createAccount(CreateAccform createAccform) {
        String role = createAccform.getRole().get(0).substring(0,1);
        LocalDateTime dateTimeStamp = LocalDateTime.now();
        String year = DateTimeFormatter.ofPattern("YYYY").format(dateTimeStamp);
        String milisecond = String.valueOf(System.currentTimeMillis());
        String code = role+year+milisecond;

        AccountEntity accountEntity = new AccountEntity();
        accountConvert.toEntity(createAccform, accountEntity);
        accountEntity.setCodeAccount(code);
        return accountEntity;
    }

    @Override
    public AccountDTO createEmployeeAccount(EmployeeCreateForm form) {
        ValidateValueForm.validateNull(form);
        validateAccountForm.checkCreateAccform(form);
        AccountEntity accountEntity = createAccount(form);
        accountRepository.save(accountEntity);

        for(String roleCode: form.getRole()){
            RoleEntity roleEntity = roleRepository.findByCode(roleCode).orElseThrow(() -> new ValidateException("Không tìm thấy quyền"));
            RolesAccountsEntity rolesAccountsEntity = new RolesAccountsEntity();
            rolesAccountsEntity.setRole(roleEntity);
            rolesAccountsEntity.setAccount(accountEntity);
            rolesAcountsRepository.save(rolesAccountsEntity);
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setCertificate(form.getCertificate());
        employeeEntity.setAccount(accountEntity);
        employeeRepository.save(employeeEntity);
        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public AccountDTO createAdminAccount(CreateAccform form) {
        ValidateValueForm.validateNull(form);
        validateAccountForm.checkCreateAccform(form);
        AccountEntity accountEntity = createAccount(form);

        RoleEntity roleEntity = roleRepository.findByCode(form.getRole().get(0)).orElseThrow(() -> new ValidateException("Không tìm thấy quyền"));

        RolesAccountsEntity rolesAccountsEntity = new RolesAccountsEntity();
        rolesAccountsEntity.setRole(roleEntity);
        rolesAccountsEntity.setAccount(accountEntity);
        accountEntity.getRolesAccountsEntities().add(rolesAccountsEntity);

        accountRepository.save(accountEntity);
        rolesAcountsRepository.save(rolesAccountsEntity);

        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public AccountDTO createStudentAccount(StudentCreateForm studentform) {
        ValidateValueForm.validateNull(studentform);
        validateAccountForm.checkCreateAccform(studentform);
        AccountEntity accountEntity = createAccount(studentform);
        accountRepository.save(accountEntity);

        for(String roleCode: studentform.getRole()){
            RoleEntity roleEntity = roleRepository.findByCode(roleCode).orElseThrow(() -> new ValidateException("Không tìm thấy quyền"));
            RolesAccountsEntity rolesAccountsEntity = new RolesAccountsEntity();
            rolesAccountsEntity.setRole(roleEntity);
            rolesAccountsEntity.setAccount(accountEntity);
            rolesAcountsRepository.save(rolesAccountsEntity);
        }

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEducation(studentform.getEducation());
        studentEntity.setAccount(accountEntity);
        studentRepository.save(studentEntity);
        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public AccountDTO findAccountById(Long id) {
        AccountEntity accountEntity = accountRepository.findById(id).orElseThrow(() -> new ValidateException("Tài khoản không tồn tại"));
        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public PageDTO<AccountDTO> getAccount(Map<String,String> params) {
        String pageStr = params.get("page");
        String limitStr = params.get("limit");
        Integer page =1;
        Integer limit = 5;

        String username = params.get("sUserName");
        String code = params.get("sCode");
        String email = params.get("sEmail");
        String phoneNumber = params.get("sPhoneNumber");
        String name = params.get("sName");
        String birthday = params.get("sBirthday");
        String roleCode = params.get("sRoleCode");

        if (CheckNullVariable.checkNullString(pageStr)){
            page = Integer.valueOf(pageStr);
        }
        if (CheckNullVariable.checkNullString(limitStr)){
            limit = Integer.valueOf(limitStr);
        }

            StringBuilder selectQueryBuilder = new StringBuilder("Select r from AccountEntity r join r.rolesAccountsEntities rRole where r.isDeleted = false ");
        StringBuilder countQueryBuilder = new StringBuilder("Select count(r) from AccountEntity r join r.rolesAccountsEntities rRole  where r.isDeleted = false ");


        if (CheckNullVariable.checkNullString(username)) {
            selectQueryBuilder.append("and r.username like :username ");
            countQueryBuilder.append("and r.username like :username ");
        }

        if (CheckNullVariable.checkNullString(code)) {
            selectQueryBuilder.append("and r.codeAccount like :code ");
            countQueryBuilder.append("and r.codeAccount like :code ");
        }

        if (CheckNullVariable.checkNullString(email)) {
            selectQueryBuilder.append("and r.email like :email ");
            countQueryBuilder.append("and r.email like :email ");
        }

        if (CheckNullVariable.checkNullString(phoneNumber)) {
            selectQueryBuilder.append("and r.phoneNumber like :phoneNumber ");
            countQueryBuilder.append("and r.phoneNumber like :phoneNumber ");
        }

        if (CheckNullVariable.checkNullString(name)) {
            selectQueryBuilder.append("and r.name like :name ");
            countQueryBuilder.append("and r.name like :name ");
        }

        if (CheckNullVariable.checkNullString(birthday)) {
            selectQueryBuilder.append("and r.birthday like :birthday ");
            countQueryBuilder.append("and r.birthday like :birthday ");
        }

        if (CheckNullVariable.checkNullString(roleCode)) {
            selectQueryBuilder.append("and rRole.role.code = :roleCode ");
            countQueryBuilder.append("and rRole.role.code = :roleCode ");
        }

        Integer firstItem = (page - 1)*limit;

        TypedQuery<AccountEntity> selectQuery = entityManager.createQuery(selectQueryBuilder.toString(), AccountEntity.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countQueryBuilder.toString(), Long.class);

        if (CheckNullVariable.checkNullString(username)) {
            selectQuery.setParameter("username", "%" + username + "%");
            countQuery.setParameter("username", "%" + username + "%");
        }

        if (CheckNullVariable.checkNullString(code)) {
            selectQuery.setParameter("code", "%" + code + "%");
            countQuery.setParameter("code", "%" + code + "%");
        }

        if (CheckNullVariable.checkNullString(email)) {
            selectQuery.setParameter("email", "%" + email + "%");
            countQuery.setParameter("email", "%" + email + "%");
        }

        if (CheckNullVariable.checkNullString(phoneNumber)) {
            selectQuery.setParameter("phoneNumber", "%" + phoneNumber + "%");
            countQuery.setParameter("phoneNumber", "%" + phoneNumber + "%");
        }

        if (CheckNullVariable.checkNullString(name)) {
            selectQuery.setParameter("name", "%" + name + "%");
            countQuery.setParameter("name", "%" + name + "%");
        }

        if (CheckNullVariable.checkNullString(birthday)) {
            selectQuery.setParameter("birthday", "%" + birthday + "%");
            countQuery.setParameter("birthday", "%" + birthday + "%");
        }

        if (CheckNullVariable.checkNullString(roleCode)) {
            selectQuery.setParameter("roleCode", roleCode);
            countQuery.setParameter("roleCode", roleCode);
        }

        selectQuery.setFirstResult(firstItem);
        selectQuery.setMaxResults(limit);

        List<AccountEntity> accountEntityList = selectQuery.getResultList();
        Long totalItems = countQuery.getSingleResult();

        List<AccountDTO> accountDTOList = new ArrayList<>();
        for(AccountEntity accountEntity : accountEntityList){
            if(accountEntity.getIsDeleted()==false) {
                accountDTOList.add(accountConvert.toDTO(accountEntity));
            }
        }
        return new PageDTO<>(page,limit,totalItems,accountDTOList);
    }

    public AccountEntity putAccount(AccountRequest request, String id) {
        ValidateValueForm.validateNull(request);
        Long accountId = CheckNullVariable.checkValidateLong(id);
        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> new ValidateException("Không tìm thấy tài khoản"));
        accountConvert.toEntity(request, accountEntity);

        accountRepository.save(accountEntity);
        return accountEntity;
    }

    @Override
    public AccountDTO putStudentAccount(StudentRequest request, String id) {
        ValidateValueForm.validateNull(request);

        StudentEntity studentEntity =  putAccount(request,id).getStudentEntity();
        studentConvert.toEntity(request,studentEntity);
        studentRepository.save(studentEntity);

        AccountEntity accountEntity = studentEntity.getAccount();
        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public AccountDTO putEmployeeAccount(EmployeeRequest request, String id) {
        ValidateValueForm.validateNull(request);

        EmployeeEntity employeeEntity =  putAccount(request,id).getEmployeeEntity();
        employeeConvert.toEntity(request, employeeEntity);
        employeeRepository.save(employeeEntity);

        AccountEntity accountEntity = employeeEntity.getAccount();
        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public void deleteAccount(String id) {
        Long accountId = CheckNullVariable.checkValidateLong(id);

        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> new ValidateException("Không tìm thấy tài khoản"));
        if(accountEntity.getStudentEntity() != null){
            StudentEntity studentEntity =  accountEntity.getStudentEntity();
            studentEntity.setIsDeleted(true);
            studentRepository.save(studentEntity);
        }else if(accountEntity.getEmployeeEntity() != null) {
            EmployeeEntity employeeEntity =  accountEntity.getEmployeeEntity();
            employeeEntity.setIsDeleted(true);
            employeeRepository.save(employeeEntity);
        }

        if(accountEntity.getImageEntity() != null){
            ImageEntity imageEntity =  accountEntity.getImageEntity();
            imageEntity.setIsDeleted(true);
            imageRepository.save(imageEntity);
        }

        accountEntity.setIsDeleted(true);
        accountRepository.save(accountEntity);
    }

    @Override
    public AccountDTO updateEmail(PutUniqueAttributeAccountRequest request) {
        ValidateValueForm.validateNull(request);
        Long accountId = CheckNullVariable.checkValidateLong(request.getId());
        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> new ValidateException("Không tìm thấy tài khoản"));

        accountEntity.setEmail(request.getNewAttribute());
        accountRepository.save(accountEntity);
        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public AccountDTO updatePhone(PutUniqueAttributeAccountRequest request) {
        ValidateValueForm.validateNull(request);
        CheckNullVariable.checkNullNumber(request.getNewAttribute());

        if (request.getNewAttribute().length() != 10){
            throw new AuthException("Số điện thoại phải dài 10 số");
        }
        Optional<AccountEntity> optionalAccountEntityByPhone = accountRepository.findByPhone(request.getNewAttribute());
        if (optionalAccountEntityByPhone.isPresent()){
            throw new AuthException("Số điện thoại đã tồn tại");
        }

        Long accountId = CheckNullVariable.checkValidateLong(request.getId());
        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> new ValidateException("Không tìm thấy tài khoản"));

        accountEntity.setPhoneNumber(request.getNewAttribute());
        accountRepository.save(accountEntity);
        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public AccountDTO resetPassword(PutUniqueAttributeAccountRequest request) {
        ValidateValueForm.validateNull(request);
        Long accountId = CheckNullVariable.checkValidateLong(request.getId());

        if (request.getNewAttribute().length() <= 8){
            throw new AuthException("Mật khẩu phải chứa ít nhất 8 kí tự");
        }

        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> new ValidateException("Không tìm thấy tài khoản"));

        accountEntity.setPassword(request.getNewAttribute());
        accountRepository.save(accountEntity);
        return accountConvert.toDTO(accountEntity);
    }

    @Override
    public AccountDTO getAccount() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String username = userDetails.getUsername();
                AccountDTO accountDTO = accountConvert.toDTO(accountRepository.findByUserName(username).get());
                return accountDTO;
            }
            AccountDTO accountDTO = new AccountDTO();
            return accountDTO;
    }
}
