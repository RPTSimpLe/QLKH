package com.DoAn.f88.api.user;

import com.DoAn.f88.dto.StudentDTO;
import com.DoAn.f88.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class StudentApi {
    @Autowired
    private StudentService studentService;
//
//    @PostMapping("/create")
//    public StudentDTO createStudent(@RequestBody ) {}
}
