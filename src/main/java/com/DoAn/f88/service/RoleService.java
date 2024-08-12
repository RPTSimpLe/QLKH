package com.DoAn.f88.service;

import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.dto.RoleDTO;
import com.DoAn.f88.entity.RoleEntity;
import com.DoAn.f88.request.RoleRequest;

import java.util.List;
import java.util.Map;

public interface RoleService {
     List<RoleDTO> findAll();
     PageDTO<RoleDTO> findRole(Map<String,String> params);
     List<RoleDTO> findByRoleCode(String roleCode);
     List<RoleDTO> findByParentId(String parentId);
     RoleDTO findById(String id);
     RoleDTO createRole(RoleRequest roleRequest);
     RoleDTO updateRole(RoleRequest roleRequest,String id);

     void deleteRole(String id);
}
