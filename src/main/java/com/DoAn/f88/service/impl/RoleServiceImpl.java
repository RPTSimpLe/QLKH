package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.RoleConvert;
import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.dto.RoleDTO;
import com.DoAn.f88.entity.RoleEntity;
import com.DoAn.f88.exeption.Error403.CheckNullVariable;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.exeption.Error403.ValidateValueForm;
import com.DoAn.f88.exeption.Error403.role.ValidateRoleForm;
import com.DoAn.f88.repository.RoleRepository;
import com.DoAn.f88.request.RoleRequest;
import com.DoAn.f88.service.RoleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleConvert roleConvert;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ValidateRoleForm validateRoleForm;

    @Override
    public List<RoleDTO> findAll() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        return roleConvert.toDto(roleEntityList);
    }

    @Override
    public PageDTO<RoleDTO> findRole(Map<String, String> params) {
        String pageStr = params.get("page");
        String limitStr = params.get("limit");
        String code = params.get("sCode");
        String name = params.get("sName");
        String parentId = params.get("sParentId");

        Integer page = 1;
        Integer limit = 5;

        if(CheckNullVariable.checkNullString(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        if(CheckNullVariable.checkNullString(limitStr)) {
            limit = Integer.parseInt(limitStr);
        }

        StringBuilder selectStringBuilder = new StringBuilder("select r from RoleEntity r where isDeleted = false ");
        StringBuilder countStringBuilder = new StringBuilder("select count(r) from RoleEntity r where isDeleted = false ");

        if (CheckNullVariable.checkNullString(code)) {
            selectStringBuilder.append(" and r.code like :code");
            countStringBuilder.append(" and r.code like :code");
        }
        if (CheckNullVariable.checkNullString(name)) {
            selectStringBuilder.append(" and r.name like :name");
            countStringBuilder.append(" and r.name like :name");
        }
        if (CheckNullVariable.checkNullString(parentId)) {
            if (parentId.equals("1")){
                selectStringBuilder.append(" and r.id = :parentId");
                countStringBuilder.append(" and r.id = :parentId");
            }else {
                selectStringBuilder.append(" and r.parentId = :parentId");
                countStringBuilder.append(" and r.parentId = :parentId");
            }
        }

        Integer firstItem = (page - 1) * limit;

        TypedQuery<RoleEntity> selectQuery = entityManager.createQuery(selectStringBuilder.toString(), RoleEntity.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countStringBuilder.toString(), Long.class);

        if (CheckNullVariable.checkNullString(code)) {
            selectQuery.setParameter("code", "%" + code + "%");
            countQuery.setParameter("code", "%" + code + "%");
        }
        if (CheckNullVariable.checkNullString(name)) {
            selectQuery.setParameter("name", "%" + name + "%");
            countQuery.setParameter("name", "%" + name + "%");
        }
        if (CheckNullVariable.checkNullString(parentId)) {
            selectQuery.setParameter("parentId",  parentId);
            countQuery.setParameter("parentId",  parentId);

        }

        selectQuery.setFirstResult(firstItem);
        selectQuery.setMaxResults(limit);

        List<RoleEntity> roleEntityList = selectQuery.getResultList();
        Long count = countQuery.getSingleResult();

        List<RoleDTO> roleDTOList = roleConvert.toDto(roleEntityList);

        return new PageDTO<>(page, limit, count, roleDTOList);
    }

    @Override
    public List<RoleDTO> findByRoleCode(String roleCode) {
        RoleEntity roleEntity = roleRepository.findByCode(roleCode).orElseThrow(() -> new ValidateException("Không tìm thấy role"));
        List<RoleEntity> roleEntityList = roleRepository.findByParentId(roleEntity.getId());
        return roleConvert.toDto(roleEntityList);
    }

    @Override
    public List<RoleDTO> findByParentId(String parentId) {
        Long pid = CheckNullVariable.checkValidateLong(parentId);

        List<RoleEntity> roleEntityList = roleRepository.findByParentId(pid);
        return roleConvert.toDto(roleEntityList);
    }

    @Override
    public RoleDTO createRole(RoleRequest roleRequest) {
        ValidateValueForm.validateNull(roleRequest);
        validateRoleForm.ValidateUniqueValue(roleRequest);
        RoleEntity roleEntity = roleConvert.toEntity(roleRequest);
        roleRepository.save(roleEntity);
        return roleConvert.toDto(roleEntity);
    }

    @Override
    public RoleDTO findById(String id) {
        Long roleId = CheckNullVariable.checkValidateLong(id);
        RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(() -> new ValidateException("Không tìm thấy vai trò"));

        return roleConvert.toDto(roleEntity);
    }

    @Override
    public RoleDTO updateRole(RoleRequest roleRequest, String id) {
        Long roleId = CheckNullVariable.checkValidateLong(id);
        ValidateValueForm.validateNull(roleRequest);

        RoleEntity newRoleEntity = roleConvert.toEntity(roleRequest);

        RoleEntity oldRoleEntity = roleRepository.findById(roleId).orElseThrow(() -> new ValidateException("Không tìm thấy vai trò"));

        oldRoleEntity.setParentId(newRoleEntity.getParentId());
        oldRoleEntity.setName(newRoleEntity.getName());

        oldRoleEntity = roleRepository.save(oldRoleEntity);
        return roleConvert.toDto(oldRoleEntity);
    }

    @Override
    public void deleteRole(String id) {
        Long roleId = CheckNullVariable.checkValidateLong(id);

        RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(() -> new ValidateException("Không tìm thấy vai trò"));
        roleEntity.setIsDeleted(true);
        roleRepository.save(roleEntity);

        if (roleEntity.getParentId() == 0){
            List<RoleEntity> roleEntityList = roleRepository.findByParentId(roleEntity.getId());
            for(RoleEntity roleEntity2 : roleEntityList){
                roleEntity2.setIsDeleted(true);
                roleRepository.save(roleEntity2);
            }
        }

    }
}
