package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.StudentConvert;
import com.DoAn.f88.dto.StudentDTO;
import com.DoAn.f88.entity.StudentEntity;
import com.DoAn.f88.request.student.StudentCreateForm;
import com.DoAn.f88.repository.StudentRepository;
import com.DoAn.f88.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentConvert studentConvert;
    private StudentRepository studentRepository;

    @Override
    public StudentDTO createStudent(StudentCreateForm createForm) {
        StudentEntity studentEntity= new StudentEntity();
        studentConvert.toEntity(createForm, studentEntity);
        studentRepository.save(studentEntity);
        return studentConvert.toDTO(studentEntity);
    }
}
