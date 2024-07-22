package com.DoAn.f88.service;

import com.DoAn.f88.dto.StudentDTO;
import com.DoAn.f88.formCreate.StudentCreateForm;

public interface StudentService {
    public StudentDTO createStudent(StudentCreateForm createForm);
}
