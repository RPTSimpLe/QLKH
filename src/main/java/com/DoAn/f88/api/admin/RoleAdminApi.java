package com.DoAn.f88.api.admin;

import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.dto.RoleDTO;
import com.DoAn.f88.request.RoleRequest;
import com.DoAn.f88.service.RoleService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/role")
public class RoleAdminApi {
    @Autowired
    private RoleService roleService;

    @GetMapping("admin/getAll")
    public List<RoleDTO> getAll() {
        return roleService.findAll();
    }
    @GetMapping("/admin/pagiRole")
    public PageDTO<RoleDTO> pagiRole(@RequestParam Map<String,String> params){
        return roleService.findRole(params);
    }
    @GetMapping("/admin/findByRoleCode/{roleCode}")
    public List<RoleDTO> findByRoleCode(@PathVariable String roleCode) {
        return roleService.findByRoleCode(roleCode);
    }
    @GetMapping("/admin/findByParentId/{parentId}")
    public List<RoleDTO> findByParentId(@PathVariable String parentId) {
        return roleService.findByParentId(parentId);
    }
    @GetMapping("/admin/findById/{id}")
    public RoleDTO findById(@PathVariable String id) {
        return roleService.findById(id);
    }
    @PostMapping("/admin/create")
    public RoleDTO create(@RequestBody RoleRequest roleRequest) {
        return roleService.createRole(roleRequest);
    }
    @PutMapping("/admin/update/{id}")
    public RoleDTO update(@RequestBody RoleRequest roleRequest, @PathVariable String id) {
        return roleService.updateRole(roleRequest,id);
    }
    @DeleteMapping("/admin/delete/{id}")
    public void delete(@PathVariable String id){
        roleService.deleteRole(id);
    }
}
