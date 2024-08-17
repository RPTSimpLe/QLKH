package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.RoadMapConvert;
import com.DoAn.f88.dto.RoadMapDTO;
import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.entity.RoadMapEntity;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.repository.CourseRepository;
import com.DoAn.f88.repository.RoadMapRepository;
import com.DoAn.f88.request.RoadMapRequest;
import com.DoAn.f88.service.RoadMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoadMapServiceImpl implements RoadMapService {
    @Autowired
    private RoadMapRepository roadMapRepository;
    @Autowired
    private RoadMapConvert roadMapConvert;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public RoadMapDTO create(RoadMapRequest roadMapRequest) {
        RoadMapEntity roadMapEntity = roadMapConvert.toEntity(roadMapRequest);
        for(Long courseId : roadMapRequest.getCourseId()){
            CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(()-> new ValidateException("Không tìm thấy khóa học"));
//            roadMapEntity.getRoadMapCourses().a
        }
        return null;
    }
}
