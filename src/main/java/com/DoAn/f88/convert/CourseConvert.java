package com.DoAn.f88.convert;

import com.DoAn.f88.dto.CourseDTO;
import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.entity.DetailCourseEntity;
import com.DoAn.f88.request.CourseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseConvert {
    @Mapping(target = "duration", ignore = true)
    @Mapping(target = "numberPreiod", ignore = true)
    CourseDTO toDto (CourseEntity courseEntity);
    CourseEntity toEntity (CourseRequest courseRequest);

    default List<CourseDTO> toDtoList (List<CourseEntity> courseEntityList){
        List<CourseDTO> courseDTOList = new ArrayList<CourseDTO>();
        for(CourseEntity courseEntity : courseEntityList){
             CourseDTO courseDTO = toDtoCustom(courseEntity);
             courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

    List<CourseEntity> toEntityList (List<CourseDTO> courseDTOList);

    default CourseDTO toDtoCustom(CourseEntity courseEntity) {
        CourseDTO courseDTO = toDto(courseEntity);

        if (courseEntity.getDetailCourse() == null) {
            courseDTO.setDuration(0);
            courseDTO.setNumberPreiod(0);
        } else {
            Integer duration = 0;
            for (DetailCourseEntity detailCourseEntity : courseEntity.getDetailCourse()) {
                duration += detailCourseEntity.getDuration();
            }
            courseDTO.setNumberPreiod(courseEntity.getDetailCourse().size());
            courseDTO.setDuration(duration);
        }

        return courseDTO;
    }
}
