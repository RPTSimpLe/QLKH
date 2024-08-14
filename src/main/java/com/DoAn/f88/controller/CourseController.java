package com.DoAn.f88.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/courseView")
public class CourseController {
    @GetMapping("createCourse")
    public String createCourse() {
        return "admin/cousre/createCourse";
    }
    @GetMapping("listCourse")
    public String listCourse() {
        return "admin/cousre/listCourse";
    }
}
