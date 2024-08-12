package com.DoAn.f88.convert;

import com.DoAn.f88.dto.CourseDTO;
import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.request.CourseRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseConvert {
    CourseDTO toDto (CourseEntity courseEntity);
    CourseEntity toEntity (CourseRequest courseRequest);

    List<CourseDTO> toDtoList (List<CourseEntity> courseEntityList);
    List<CourseEntity> toEntityList (List<CourseDTO> courseDTOList);
}
