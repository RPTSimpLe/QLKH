package com.DoAn.f88.service;

import com.DoAn.f88.dto.CourseDTO;
import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.request.CourseRequest;

import java.util.List;
import java.util.Map;

public interface CourseService {
    CourseDTO create(CourseRequest courseRequest);
    PageDTO<CourseDTO> getAll(Map<String, String> params);
    CourseDTO findById(String id);
    CourseDTO update(CourseRequest courseRequest,String id);
    List<CourseDTO> findAll();
    void delete(String id);

    Long calculateTotalPrice(List<Long> ids);
}
