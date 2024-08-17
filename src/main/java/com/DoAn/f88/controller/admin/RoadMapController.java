package com.DoAn.f88.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/roadMapView")
public class RoadMapController {
    @GetMapping("/createRoadMap")
    public String createRoadMap() {
        return "admin/roadMap/createRoadMap";
    }
    @GetMapping("/listRoadMap")
    public String listRoadMap() {
        return "admin/roadMap/listRoadMap";
    }
}
