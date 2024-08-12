package com.DoAn.f88.service.impl;

import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.entity.RoleEntity;
import com.DoAn.f88.entity.RolesAccountsEntity;
import com.DoAn.f88.repository.RolesAcountsRepository;
import com.DoAn.f88.service.RolesAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolesAccountServiceImpl implements RolesAccountService {
    @Autowired
    private RolesAcountsRepository rolesAcountsRepository;

    @Override
    public RolesAccountsEntity create(RoleEntity roleEntity, AccountEntity accountEntity) {

        Optional<RolesAccountsEntity> otpRolesAccountsEntity = rolesAcountsRepository.findByRoleAAndAccount(roleEntity,accountEntity);
        if (!otpRolesAccountsEntity.isPresent()) {
            RolesAccountsEntity rolesAccountsEntity = otpRolesAccountsEntity.get();
            rolesAccountsEntity.setAccount(accountEntity);
            rolesAccountsEntity.setRole(roleEntity);
            rolesAcountsRepository.save(rolesAccountsEntity);
            return rolesAccountsEntity;
        }
        return null;
    }

    @Override
    public RolesAccountsEntity update(RoleEntity roleEntity, AccountEntity accountEntity) {
//        List<RolesAccountsEntity> rolesAccountsEntities = rolesAcountsRepository.findByAccount(accountEntity.getId());
//        for(RolesAccountsEntity rolesAccountsEntity : rolesAccountsEntities) {
//            if(rolesAccountsEntity.getRole().equals(roleEntity)) {
//
//            }
//        }
//        if (rolesAccountsEntities.isPresent()) {
//            RolesAccountsEntity rolesAccountsEntity = rolesAccountsEntities.get();
//            rolesAccountsEntity.setAccount(accountEntity);
//            rolesAccountsEntity.setRole(roleEntity);
//            rolesAcountsRepository.save(rolesAccountsEntity);
//            return rolesAccountsEntity;
//        }
        return null;
    }
}
