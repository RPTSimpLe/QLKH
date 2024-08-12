package com.DoAn.f88.exeption.Error403.role;

import com.DoAn.f88.entity.RoleEntity;
import com.DoAn.f88.exeption.Error403.CheckNullVariable;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.repository.RoleRepository;
import com.DoAn.f88.request.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidateRoleForm {
    @Autowired
    private RoleRepository roleRepository;

    public void ValidateUniqueValue(RoleRequest roleRequest) {
        Optional<RoleEntity> roleEntity1 = roleRepository.findByName(roleRequest.getName());
        if (roleEntity1.isPresent()) {
            throw new ValidateException("Tên đã tồn tại");
        }

        Optional<RoleEntity> roleEntity = roleRepository.findByCode(roleRequest.getCode());
        if (roleEntity.isPresent()) {
            throw new ValidateException("Mã đã tồn tại");
        }
    }
}
