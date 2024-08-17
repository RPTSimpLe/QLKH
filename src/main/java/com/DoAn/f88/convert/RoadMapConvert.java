package com.DoAn.f88.convert;

import com.DoAn.f88.dto.RoadMapDTO;
import com.DoAn.f88.entity.RoadMapEntity;
import com.DoAn.f88.request.RoadMapRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoadMapConvert {
    RoadMapDTO toDto(RoadMapEntity roadMapEntity);
    RoadMapEntity toEntity(RoadMapRequest roadMapRequest);

    List<RoadMapDTO> toDtoList(List<RoadMapEntity> roadMapEntityList);
    List<RoadMapEntity> toEntityList(List<RoadMapRequest> roadMapRequestList);
}
