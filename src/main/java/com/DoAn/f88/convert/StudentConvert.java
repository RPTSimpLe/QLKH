package com.DoAn.f88.convert;

import com.DoAn.f88.dto.StudentDTO;
import com.DoAn.f88.entity.StudentEntity;
import com.DoAn.f88.formCreate.StudentCreateForm;
import org.springframework.stereotype.Component;

@Component
public class StudentConvert {
    public StudentEntity toEntity(StudentCreateForm form){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEducation(form.getEducation());
        return studentEntity;
    }

    public StudentDTO toDTO(StudentEntity studentEntity){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setEducation(studentEntity.getEducation());
        return studentDTO;
    }
}
