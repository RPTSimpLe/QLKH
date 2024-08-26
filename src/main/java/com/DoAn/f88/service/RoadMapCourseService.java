package com.DoAn.f88.service;

import com.DoAn.f88.dto.RoadMapCourseDTO;
import com.DoAn.f88.request.RoadMapCourseRequest;

public interface RoadMapCourseService {
    RoadMapCourseDTO create(RoadMapCourseRequest roadMapCourseRequest);
    void delete(Long roadMapId);
}
