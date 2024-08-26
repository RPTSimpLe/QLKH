package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.RoadMapCourseConvert;
import com.DoAn.f88.dto.RoadMapCourseDTO;
import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.entity.RoadMapCourseEntity;
import com.DoAn.f88.entity.RoadMapEntity;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.exeption.Error403.ValidateValueForm;
import com.DoAn.f88.repository.CourseRepository;
import com.DoAn.f88.repository.RoadMapCourseRepository;
import com.DoAn.f88.repository.RoadMapRepository;
import com.DoAn.f88.request.RoadMapCourseRequest;
import com.DoAn.f88.service.RoadMapCourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoadMapCourseServiceImpl implements RoadMapCourseService {
    @Autowired
    private RoadMapCourseRepository roadMapCourseRepository;
    @Autowired
    private RoadMapCourseConvert roadMapCourseConvert;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RoadMapRepository roadMapRepository;

    @Override
    public RoadMapCourseDTO create(RoadMapCourseRequest roadMapCourseRequest) {
        ValidateValueForm.validateNull(roadMapCourseRequest);
        CourseEntity courseEntity = courseRepository.findById(roadMapCourseRequest.getCourseId()).orElseThrow(() -> new ValidateException("Không tìm  thấy khóa học"));
        RoadMapEntity roadMapEntity = roadMapRepository.findById(roadMapCourseRequest.getRoadMapId()).orElseThrow(() -> new ValidateException("Không tìm  thấy lộ trình"));

        RoadMapCourseEntity roadMapCourseEntity = new RoadMapCourseEntity();
        roadMapCourseEntity.setCourse(courseEntity);
        roadMapCourseEntity.setRoadMap(roadMapEntity);

        roadMapCourseEntity = roadMapCourseRepository.save(roadMapCourseEntity);
        return roadMapCourseConvert.toDto(roadMapCourseEntity);
    }

    @Override
    public void delete(Long roadMapId) {
        List<RoadMapCourseEntity> roadMapCourseEntityList = roadMapCourseRepository.findByRoadMapId(roadMapId);
        for (RoadMapCourseEntity roadMapCourseEntity : roadMapCourseEntityList) {
            roadMapCourseRepository.delete(roadMapCourseEntity);
        }
    }
}
