package com.DoAn.f88.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/detailCourseView")
public class DetailCourseController {
    @GetMapping("/getDetailCourse/{id}")
    public String listCourse(@PathVariable Long id, ModelAndView model) {
        model.addObject("id", id);
        return "admin/detailCourse/getDetailCourse";
    }
}
