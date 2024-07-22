package com.DoAn.f88.convert;

import com.DoAn.f88.dto.account.AccountDTO;
import com.DoAn.f88.formCreate.CreateAccform;
import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.entity.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountConvert {
    public AccountEntity toEntity(CreateAccform createAccForm){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(createAccForm.getName());
        accountEntity.setUsername(createAccForm.getUsername());
        accountEntity.setPassword(createAccForm.getPassword());
        accountEntity.setEmail(createAccForm.getEmail());
        accountEntity.setPhoneNumber(createAccForm.getPhoneNumber());
        accountEntity.setGender(createAccForm.getGender());
        accountEntity.setBirthday(createAccForm.getBirthday());
        return accountEntity;
    }

    public AccountDTO toDTO(AccountEntity accountEntity){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(accountEntity.getId());
        accountDTO.setUsername(accountEntity.getUsername());
        accountDTO.setPassword(accountEntity.getPassword());
        accountDTO.setEmail(accountEntity.getEmail());
        accountDTO.setName(accountEntity.getName());
        accountDTO.setPhoneNumber(accountEntity.getPhoneNumber());
        accountDTO.setGender(accountEntity.getGender());
        accountDTO.setBirthday(accountEntity.getBirthday());
        List<RoleEntity> roleEntities = accountEntity.getRoles();
        for(RoleEntity roleEntity : roleEntities){
            accountDTO.getRoleName().add(roleEntity.getName());
            accountDTO.getRoleCode().add(roleEntity.getCode());
        }
        accountDTO.setCreatedBy(accountEntity.getCreateBy());
        accountDTO.setCreatedDate(accountEntity.getCreateDate());
        accountDTO.setModifierBy(accountEntity.getModifierBy());
        accountDTO.setModifierDate(accountEntity.getModifierDate());
        return accountDTO;
    }
}
