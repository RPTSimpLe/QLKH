package com.DoAn.f88.service;

import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.dto.RoadMapDTO;
import com.DoAn.f88.request.RoadMapRequest;

import java.util.Map;

public interface RoadMapService {
    RoadMapDTO create(RoadMapRequest roadMapRequest);
    PageDTO<RoadMapDTO> getAll(Map<String, String> params);
    RoadMapDTO findById(String id);
    void delete(String id);
}
