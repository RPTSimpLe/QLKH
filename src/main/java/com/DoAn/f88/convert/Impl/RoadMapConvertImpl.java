package com.DoAn.f88.convert.Impl;

import com.DoAn.f88.convert.RoadMapConvert;
import com.DoAn.f88.dto.RoadMapDTO;
import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.entity.RoadMapCourseEntity;
import com.DoAn.f88.entity.RoadMapEntity;
import com.DoAn.f88.request.RoadMapRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoadMapConvertImpl implements RoadMapConvert {
    @Override
    public RoadMapDTO toDto(RoadMapEntity roadMapEntity) {
        RoadMapDTO roadMapDTO = new RoadMapDTO();
        roadMapDTO.setCreatedBy(roadMapEntity.getCreateBy());
        roadMapDTO.setIsDeleted(roadMapEntity.getIsDeleted());
        roadMapDTO.setCreatedDate(roadMapEntity.getCreateDate());
        roadMapDTO.setModifierDate(roadMapEntity.getModifierDate());
        roadMapDTO.setModifierBy(roadMapEntity.getModifierBy());

        roadMapDTO.setId(roadMapEntity.getId());
        roadMapDTO.setName(roadMapEntity.getName());
        roadMapDTO.setCode(roadMapEntity.getCode());
        roadMapDTO.setDescription(roadMapEntity.getDescription());
        roadMapDTO.setDiscount(roadMapEntity.getDiscount());

        long totalPrice = 0;
        long totalPeriod = 0;
        for (RoadMapCourseEntity roadMapCourseEntity: roadMapEntity.getRoadMapCourses()){
            totalPrice+= roadMapCourseEntity.getCourse().getPrice();
            totalPeriod += roadMapCourseEntity.getCourse().getDetailCourse().size();
            roadMapDTO.getNameCourse().add(roadMapCourseEntity.getCourse().getName());
        }
        roadMapDTO.setTotalPrice(totalPrice);
        roadMapDTO.setTotalPreiod(totalPeriod);
        return roadMapDTO;
    }

    @Override
    public RoadMapEntity toEntity(RoadMapRequest roadMapRequest) {
        RoadMapEntity roadMapEntity = new RoadMapEntity();
        roadMapEntity.setCode(roadMapRequest.getCode());
        roadMapEntity.setName(roadMapRequest.getName());
        roadMapEntity.setDescription(roadMapRequest.getDescription());
        roadMapEntity.setDiscount(roadMapRequest.getDiscount());

        return roadMapEntity;
    }

    @Override
    public List<RoadMapDTO> toDtoList(List<RoadMapEntity> roadMapEntityList) {
        List<RoadMapDTO> roadMapDTOList = new ArrayList<RoadMapDTO>();
        for (RoadMapEntity roadMapEntity : roadMapEntityList) {
            roadMapDTOList.add(toDto(roadMapEntity));
        }
        return roadMapDTOList;
    }

}
