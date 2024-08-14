package com.DoAn.f88.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetailCourseRequest {
    private String namePreiod;
    private Long numberPreiod;
    private Integer duration;
    private String recordUrl;
    private String description;
}
