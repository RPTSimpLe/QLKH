package com.DoAn.f88.service;

import com.DoAn.f88.dto.RoadMapDTO;
import com.DoAn.f88.request.RoadMapRequest;

public interface RoadMapService {
    RoadMapDTO create(RoadMapRequest roadMapRequest);
}
