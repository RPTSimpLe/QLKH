package com.DoAn.f88.convert;

import com.DoAn.f88.dto.RoadMapCourseDTO;
import com.DoAn.f88.dto.RoadMapDTO;
import com.DoAn.f88.entity.RoadMapCourseEntity;
import com.DoAn.f88.request.RoadMapCourseRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoadMapCourseConvert {
    RoadMapCourseDTO toDto(RoadMapCourseEntity entity);
    RoadMapCourseEntity toEntity(RoadMapCourseRequest request);

    List<RoadMapCourseDTO> toDto(List<RoadMapCourseEntity> entities);
    List<RoadMapCourseEntity> toEntity(List<RoadMapCourseDTO> dtos);
}
