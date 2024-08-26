package com.DoAn.f88.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoadMapCourseRequest {
    private Long courseId;
    private Long roadMapId;

    public RoadMapCourseRequest() {
    }
    public RoadMapCourseRequest(Long courseId, Long roadMapId) {
        this.courseId = courseId;
        this.roadMapId = roadMapId;
    }
}
