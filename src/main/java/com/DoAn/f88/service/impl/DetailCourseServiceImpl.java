package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.DetailCourseConvert;
import com.DoAn.f88.dto.DetailCourseDTO;
import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.entity.DetailCourseEntity;
import com.DoAn.f88.exeption.Error403.CheckNullVariable;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.exeption.Error403.ValidateValueForm;
import com.DoAn.f88.repository.CourseRepository;
import com.DoAn.f88.repository.DetailCourseRepository;
import com.DoAn.f88.request.DetailCourseRequest;
import com.DoAn.f88.service.DetailCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DetailCourseServiceImpl implements DetailCourseService {
    @Autowired
    private DetailCourseRepository detailCourseRepository;
    @Autowired
    private DetailCourseConvert detailCourseConvert;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<DetailCourseDTO> getAll(String course_id) {
        Long coruseId = CheckNullVariable.checkValidateLong(course_id);
        List<DetailCourseEntity> detailCourseEntities = detailCourseRepository.findAll(coruseId);
        return detailCourseConvert.toDtoList(detailCourseEntities);
    }

    @Override
    public DetailCourseDTO create(DetailCourseRequest detailCourseRequest) {
        ValidateValueForm.validateNull(detailCourseRequest);
        CourseEntity courseEntity = courseRepository.findById(detailCourseRequest.getCourse_id()).orElseThrow(() -> new ValidateException("Không tìm thấy buổi học"));
        DetailCourseEntity detailCourseEntity = detailCourseConvert.toEntity(detailCourseRequest);
        detailCourseEntity.setCourse(courseEntity);

        DetailCourseEntity savedDetailCourseEntity = detailCourseRepository.save(detailCourseEntity);
        return detailCourseConvert.toDto(savedDetailCourseEntity);
    }

    @Override
    public DetailCourseDTO update(DetailCourseRequest detailCourseRequest, String id) {
        Long detailId = CheckNullVariable.checkValidateLong(id);
        DetailCourseEntity oldDetailCourseEntity = detailCourseRepository.findById(detailId).orElseThrow(() -> new ValidateException("Không tìm thấy buổi học"));
        DetailCourseEntity newDetailCourseEntity = detailCourseConvert.toEntity(detailCourseRequest);

        oldDetailCourseEntity.setDuration(newDetailCourseEntity.getDuration());
        oldDetailCourseEntity.setNamePreiod(newDetailCourseEntity.getNamePreiod());
        oldDetailCourseEntity.setDescription(oldDetailCourseEntity.getDescription());
        oldDetailCourseEntity.setNumberPreiod(oldDetailCourseEntity.getNumberPreiod());
        oldDetailCourseEntity.setRecordUrl(oldDetailCourseEntity.getRecordUrl());

        DetailCourseEntity updateDetailCourseEntity = detailCourseRepository.save(oldDetailCourseEntity);
        return detailCourseConvert.toDto(updateDetailCourseEntity);
    }

    @Override
    public void deleteAll(String course_id) {
        Long coruseId = CheckNullVariable.checkValidateLong(course_id);
        List<DetailCourseEntity> detailCourseEntities = detailCourseRepository.findAll(coruseId);
        for (DetailCourseEntity detailCourseEntity : detailCourseEntities) {
            detailCourseRepository.delete(detailCourseEntity);
        }
    }

    @Override
    public void delete(String id) {
        Long detailCoruseId = CheckNullVariable.checkValidateLong(id);
        DetailCourseEntity detailCourseEntity = detailCourseRepository.findById(detailCoruseId).orElseThrow(() -> new ValidateException("Không tìm thấy buổi học"));
        detailCourseRepository.delete(detailCourseEntity);
    }
}
