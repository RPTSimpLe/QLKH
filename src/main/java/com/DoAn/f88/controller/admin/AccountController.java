package com.DoAn.f88.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/accountView")
public class AccountController {
    @GetMapping("listAccount")
    public String listAccount() {
        return "admin/account/listAccount";
    }
}
