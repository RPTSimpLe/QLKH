package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.DetailCourseConvert;
import com.DoAn.f88.dto.DetailCourseDTO;
import com.DoAn.f88.entity.DetailCourseEntity;
import com.DoAn.f88.exeption.Error403.CheckNullVariable;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.exeption.Error403.ValidateValueForm;
import com.DoAn.f88.repository.DetailCourseRepository;
import com.DoAn.f88.request.DetailCourseRequest;
import com.DoAn.f88.service.DetailCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailCourseServiceImpl implements DetailCourseService {
    @Autowired
    private DetailCourseRepository detailCourseRepository;
    @Autowired
    private DetailCourseConvert detailCourseConvert;

    @Override
    public List<DetailCourseDTO> getAll() {
        List<DetailCourseEntity> detailCourseEntities = detailCourseRepository.findAll();
        return detailCourseConvert.toDtoList(detailCourseEntities);
    }

    @Override
    public DetailCourseDTO create(DetailCourseRequest detailCourseRequest) {
        ValidateValueForm.validateNull(detailCourseRequest);
        DetailCourseEntity detailCourseEntity = detailCourseConvert.toEntity(detailCourseRequest);

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
}
