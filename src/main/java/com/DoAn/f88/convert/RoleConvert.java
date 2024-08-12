package com.DoAn.f88.convert;

import com.DoAn.f88.dto.RoleDTO;
import com.DoAn.f88.entity.RoleEntity;
import com.DoAn.f88.request.RoleRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleConvert {
    RoleDTO toDto(RoleEntity role);
    RoleEntity toEntity(RoleRequest request);

    List<RoleDTO> toDto(List<RoleEntity> roles);
    List<RoleEntity> toEntity(List<RoleRequest> roles);
}
