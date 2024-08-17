package com.DoAn.f88.api.admin;

import com.DoAn.f88.dto.RoadMapDTO;
import com.DoAn.f88.request.RoadMapRequest;
import com.DoAn.f88.service.RoadMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roadmap/admin")
public class RoadMapAdminApi {
    @Autowired
    private RoadMapService roadMapService;

    @PostMapping("/create")
    public RoadMapDTO create(@RequestBody RoadMapRequest roadMapRequest) {
        return roadMapService.create(roadMapRequest);
    }
}
