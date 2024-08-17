package com.DoAn.f88.api.admin;

import com.DoAn.f88.dto.DetailCourseDTO;
import com.DoAn.f88.request.CourseRequest;
import com.DoAn.f88.request.DetailCourseRequest;
import com.DoAn.f88.service.CourseService;
import com.DoAn.f88.service.DetailCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detailCourses/admin")
public class DetailCourseAdminApi {
    @Autowired
    private DetailCourseService detailCourseService;

    @GetMapping("/getAll/{course_id}")
    public List<DetailCourseDTO> getAll(@PathVariable String course_id){
        return detailCourseService.getAll(course_id);
    }
    @PostMapping("/create")
    public DetailCourseDTO create(@RequestBody DetailCourseRequest request){
        return detailCourseService.create(request);
    }
    @PutMapping("/update/{id}")
    public DetailCourseDTO update(@RequestBody DetailCourseRequest request,@PathVariable String id){
        return detailCourseService.update(request, id);
    }
    @DeleteMapping("/deleteAll/{course_id}")
    public void deleteAll(@PathVariable String course_id){
        detailCourseService.deleteAll(course_id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        detailCourseService.delete(id);
    }
}
