package com.DoAn.f88.service;

import com.DoAn.f88.dto.StudentDTO;
import com.DoAn.f88.request.student.StudentCreateForm;

public interface StudentService {
    public StudentDTO createStudent(StudentCreateForm createForm);
}
