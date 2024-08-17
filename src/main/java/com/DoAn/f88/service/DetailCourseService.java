package com.DoAn.f88.service;

import com.DoAn.f88.dto.DetailCourseDTO;
import com.DoAn.f88.entity.DetailCourseEntity;
import com.DoAn.f88.request.DetailCourseRequest;

import java.util.List;

public interface DetailCourseService {
    List<DetailCourseDTO> getAll(String course_id);
    DetailCourseDTO create(DetailCourseRequest detailCourseRequest);
    DetailCourseDTO update(DetailCourseRequest detailCourseRequest, String id);

    void deleteAll(String course_id);
    void delete(String id);
}
