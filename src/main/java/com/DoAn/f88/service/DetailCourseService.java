package com.DoAn.f88.service;

import com.DoAn.f88.dto.DetailCourseDTO;
import com.DoAn.f88.entity.DetailCourseEntity;
import com.DoAn.f88.request.DetailCourseRequest;

import java.util.List;

public interface DetailCourseService {
    List<DetailCourseDTO> getAll();
    DetailCourseDTO create(DetailCourseRequest detailCourseRequest);
    DetailCourseDTO update(DetailCourseRequest detailCourseRequest, String id);
}
