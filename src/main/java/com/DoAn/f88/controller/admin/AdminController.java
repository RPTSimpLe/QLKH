package com.DoAn.f88.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping()
    public String homePage() {
        return "admin/homePageAdmin";
    }

    @GetMapping("createAccount")
    public String createAccount() {
        return "admin/account/createAccount";
    }
}
