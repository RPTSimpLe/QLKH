package com.DoAn.f88.api.admin;

import com.DoAn.f88.dto.DetailCourseDTO;
import com.DoAn.f88.request.CourseRequest;
import com.DoAn.f88.request.DetailCourseRequest;
import com.DoAn.f88.service.CourseService;
import com.DoAn.f88.service.DetailCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detailCourses/api")
public class DetailCourseAdminApi {
    @Autowired
    private DetailCourseService detailCourseService;

    @GetMapping("/getAll")
    public List<DetailCourseDTO> getAll(){
        return detailCourseService.getAll();
    }
    @PostMapping("/create")
    public DetailCourseDTO create(@RequestBody DetailCourseRequest request){
        return detailCourseService.create(request);
    }
    @PutMapping("/update")
    public DetailCourseDTO update(@RequestBody DetailCourseRequest request,@PathVariable String id){
        return detailCourseService.update(request, id);
    }
}
