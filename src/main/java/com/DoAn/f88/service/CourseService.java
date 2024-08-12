package com.DoAn.f88.service;

import com.DoAn.f88.dto.CourseDTO;
import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.request.CourseRequest;

import java.util.Map;

public interface CourseService {
    CourseDTO create(CourseRequest courseRequest);
    PageDTO<CourseDTO> getAll(Map<String, String> params);
}
