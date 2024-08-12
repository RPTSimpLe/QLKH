package com.DoAn.f88.convert;

import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.entity.RolesAccountsEntity;
import com.DoAn.f88.request.account.AccountRequest;
import com.DoAn.f88.request.account.CreateAccform;
import com.DoAn.f88.entity.AccountEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountConvert {
    public AccountEntity toEntity(CreateAccform createAccForm, AccountEntity accountEntity){
        accountEntity.setName(createAccForm.getName());
        accountEntity.setUsername(createAccForm.getUsername());
        accountEntity.setPassword(createAccForm.getPassword());
        accountEntity.setEmail(createAccForm.getEmail());
        accountEntity.setPhoneNumber(createAccForm.getPhoneNumber());
        accountEntity.setGender(createAccForm.getGender());
        accountEntity.setBirthday(createAccForm.getBirthday());
        return accountEntity;
    }

    public AccountEntity toEntity(AccountRequest request, AccountEntity accountEntity){
        accountEntity.setName(request.getName());
        accountEntity.setGender(request.getGender());
        accountEntity.setBirthday(request.getBirthday());
        return accountEntity;
    }

    public AccountDTO toDTO(AccountEntity accountEntity){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(accountEntity.getId());
        accountDTO.setUsername(accountEntity.getUsername());
        accountDTO.setEmail(accountEntity.getEmail());
        accountDTO.setName(accountEntity.getName());
        accountDTO.setPhoneNumber(accountEntity.getPhoneNumber());
        accountDTO.setGender(accountEntity.getGender());
        accountDTO.setBirthday(accountEntity.getBirthday());
        accountDTO.setCodeAccount(accountEntity.getCodeAccount());
        accountDTO.setIsDeleted(accountEntity.getIsDeleted());
        List<RolesAccountsEntity> rolesAccountsEntityList = accountEntity.getRolesAccountsEntities();
        if (accountEntity.getImageEntity() != null ){
            accountDTO.setImageId(accountEntity.getImageEntity().getId());
            accountDTO.setUrl(accountEntity.getImageEntity().getUrl());
        }

        for (RolesAccountsEntity rolesAccountsEntity : rolesAccountsEntityList) {
            accountDTO.getRoleName().add(rolesAccountsEntity.getRole().getName());
            accountDTO.getRoleCode().add(rolesAccountsEntity.getRole().getCode());
        }
        if (accountEntity.getStudentEntity() != null) {
            accountDTO.setExtendAttribute(accountEntity.getStudentEntity().getEducation());
        } else if (accountEntity.getEmployeeEntity() != null) {
            accountDTO.setExtendAttribute(accountEntity.getEmployeeEntity().getCertificate());
        }
        accountDTO.setCreatedBy(accountEntity.getCreateBy());
        accountDTO.setCreatedDate(accountEntity.getCreateDate());
        accountDTO.setModifierBy(accountEntity.getModifierBy());
        accountDTO.setModifierDate(accountEntity.getModifierDate());
        return accountDTO;
    }
}
