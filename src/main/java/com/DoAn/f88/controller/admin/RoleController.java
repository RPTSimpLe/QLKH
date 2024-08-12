package com.DoAn.f88.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/roleView")
public class RoleController {
    @GetMapping("/createRole")
    public String createRole() {
        return "admin/role/createRole";
    }

    @GetMapping("/listRole")
    public String listRole() {
        return "admin/role/listRole";
    }
}
