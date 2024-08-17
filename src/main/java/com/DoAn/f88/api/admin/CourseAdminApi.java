package com.DoAn.f88.api.admin;

import com.DoAn.f88.dto.CourseDTO;
import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.request.CourseRequest;
import com.DoAn.f88.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping("/admin/findById/{id}")
    public CourseDTO findById(@PathVariable String id) {
        return courseService.findById(id);
    }

    @PutMapping("/admin/update/{id}")
    public CourseDTO update(@RequestBody CourseRequest courseRequest,@PathVariable String id) {
        return courseService.update(courseRequest,id);
    }
    @DeleteMapping("/admin/delete/{id}")
    public void update(@PathVariable String id) {
        courseService.delete(id);
    }
    @GetMapping("admin/findAll")
    public List<CourseDTO> findAll() {
        return courseService.findAll();
    }
}
