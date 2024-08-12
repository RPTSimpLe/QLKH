package com.DoAn.f88.api.admin;

import com.DoAn.f88.dto.CourseDTO;
import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.request.CourseRequest;
import com.DoAn.f88.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/course")
public class CourseAdminApi {
    @Autowired
    private CourseService courseService;

    @PostMapping("admin/create")
    public CourseDTO create(@RequestBody CourseRequest courseRequest) {
        return courseService.create(courseRequest);
    }
    @GetMapping("/admin/getAll")
    public PageDTO<CourseDTO> getAll(@RequestParam Map<String, String> params) {
        return courseService.getAll(params);
    }
}
