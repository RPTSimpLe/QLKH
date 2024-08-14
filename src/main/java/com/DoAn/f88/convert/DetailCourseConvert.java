package com.DoAn.f88.convert;

import com.DoAn.f88.dto.DetailCourseDTO;
import com.DoAn.f88.entity.DetailCourseEntity;
import com.DoAn.f88.request.DetailCourseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetailCourseConvert {
    DetailCourseDTO toDto(DetailCourseEntity detailCourseEntity);
    DetailCourseEntity toEntity(DetailCourseRequest detailCourseDTO);
    List<DetailCourseDTO> toDtoList(List<DetailCourseEntity> detailCourseEntities);
    List<DetailCourseEntity> toEntityList(List<DetailCourseRequest> detailCourseRequests);
}
