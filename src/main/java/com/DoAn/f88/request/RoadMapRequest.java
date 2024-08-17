package com.DoAn.f88.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoadMapRequest {
    private String code;
    private String name;
    private String discount;
    private String description;
    private List<Long> courseId;
}
