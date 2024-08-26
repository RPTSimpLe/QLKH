package com.DoAn.f88.api.admin;

import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.dto.RoadMapDTO;
import com.DoAn.f88.request.RoadMapRequest;
import com.DoAn.f88.service.RoadMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/v1/roadmap")
public class RoadMapAdminApi {
    @Autowired
    private RoadMapService roadMapService;

    @PostMapping("/create")
    public RoadMapDTO create(@RequestBody RoadMapRequest roadMapRequest) {
        return roadMapService.create(roadMapRequest);
    }
    @GetMapping("/getAll")
    public PageDTO<RoadMapDTO> getAll(@RequestParam Map<String, String> params) {
        return roadMapService.getAll(params);
    }
    @GetMapping("/findById/{id}")
    public RoadMapDTO findById(@PathVariable String id) {
        return roadMapService.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        roadMapService.delete(id);
    }
}
