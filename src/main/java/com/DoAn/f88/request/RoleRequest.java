package com.DoAn.f88.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleRequest {
    private Long parentId;
    private String name;
    private String code;
}
