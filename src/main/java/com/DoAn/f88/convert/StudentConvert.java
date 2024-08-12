package com.DoAn.f88.convert;

import com.DoAn.f88.dto.StudentDTO;
import com.DoAn.f88.entity.StudentEntity;
import com.DoAn.f88.request.student.StudentCreateForm;
import com.DoAn.f88.request.student.StudentRequest;
import org.springframework.stereotype.Component;

@Component
public class StudentConvert {
    public StudentEntity toEntity(StudentCreateForm form, StudentEntity studentEntity){
        studentEntity.setEducation(form.getEducation());
        return studentEntity;
    }

    public StudentEntity toEntity(StudentRequest request, StudentEntity studentEntity){
        studentEntity.setEducation(request.getEducation());
        return studentEntity;
    }

    public StudentDTO toDTO(StudentEntity studentEntity){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setEducation(studentEntity.getEducation());
        return studentDTO;
    }
}
