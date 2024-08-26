package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.RoadMapConvert;
import com.DoAn.f88.dto.CourseDTO;
import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.dto.RoadMapDTO;
import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.entity.RoadMapEntity;
import com.DoAn.f88.exeption.Error403.CheckNullVariable;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.exeption.Error403.ValidateValueForm;
import com.DoAn.f88.repository.CourseRepository;
import com.DoAn.f88.repository.RoadMapRepository;
import com.DoAn.f88.request.RoadMapCourseRequest;
import com.DoAn.f88.request.RoadMapRequest;
import com.DoAn.f88.service.RoadMapCourseService;
import com.DoAn.f88.service.RoadMapService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoadMapServiceImpl implements RoadMapService {
    @Autowired
    private RoadMapRepository roadMapRepository;
    @Autowired
    private RoadMapConvert roadMapConvert;
    @Autowired
    private RoadMapCourseService roadMapCourseService;
    @Autowired
    private EntityManager entityManager;

    @Override
    public RoadMapDTO create(RoadMapRequest roadMapRequest) {
        ValidateValueForm.validateNull(roadMapRequest);
        RoadMapEntity roadMapEntity = roadMapConvert.toEntity(roadMapRequest);
        roadMapEntity = roadMapRepository.save(roadMapEntity);
        for(Long courseId : roadMapRequest.getCourseId()){
            RoadMapCourseRequest roadMapCourseRequest = new RoadMapCourseRequest(courseId, roadMapEntity.getId());
            roadMapCourseService.create(roadMapCourseRequest);
        }
        return roadMapConvert.toDto(roadMapEntity);
    }

    @Override
    public PageDTO<RoadMapDTO> getAll(Map<String, String> params) {
        String pageStr = params.get("page");
        String limitStr = params.get("limit");
        String sCode = params.get("sCode");
        String sName = params.get("sName");

        Integer page =1;
        Integer limit =5;

        if(CheckNullVariable.checkNullString(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(CheckNullVariable.checkNullString(limitStr)){
            limit = Integer.parseInt(limitStr);
        }

        StringBuilder selectStringBuilder = new StringBuilder("SELECT r from RoadMapEntity r " +
                                                            "where r.isDeleted = false ");
        StringBuilder countBuilder = new StringBuilder("SELECT count(r.id) FROM RoadMapEntity r " +
                                                        "where r.isDeleted = false ");

        if (CheckNullVariable.checkNullString(sCode)){
            selectStringBuilder.append(" and r.code like :sCode ");
            countBuilder.append(" and r.code like :sCode ");
        }
        if (CheckNullVariable.checkNullString(sName)){
            selectStringBuilder.append(" and r.name like :sName ");
            countBuilder.append(" and r.name like :sName ");
        }

        TypedQuery<RoadMapEntity> selectQuery = entityManager.createQuery(selectStringBuilder.toString(), RoadMapEntity.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countBuilder.toString(), Long.class);

        if (CheckNullVariable.checkNullString(sCode)){
            selectQuery.setParameter("sCode", "%" + sCode + "%");
            countQuery.setParameter("sCode", "%" + sCode + "%");
        }
        if (CheckNullVariable.checkNullString(sName)){
            selectQuery.setParameter("sName", "%" + sName + "%");
            countQuery.setParameter("sName", "%" + sName + "%");
        }

        Integer firstItem = (page - 1) * limit;
        selectQuery.setFirstResult(firstItem);
        selectQuery.setMaxResults(limit);

        List<RoadMapEntity> roadMapEntityList = selectQuery.getResultList();
        Long count = countQuery.getSingleResult();

        List<RoadMapDTO> roadMapDTOList  = roadMapConvert.toDtoList(roadMapEntityList);
        return new PageDTO<>(page, limit, count, roadMapDTOList);
    }

    @Override
    public RoadMapDTO findById(String id) {
        Long roadMapId = CheckNullVariable.checkValidateLong(id);
        RoadMapEntity roadMapEntity = roadMapRepository.findById(roadMapId).orElseThrow(()->  new ValidateException("Không tìm thấy lộ trình"));
        return roadMapConvert.toDto(roadMapEntity);
    }

    @Override
    public void delete(String id) {
        Long roadMapId= CheckNullVariable.checkValidateLong(id);
        RoadMapEntity roadMapEntity = roadMapRepository.findById(roadMapId).orElseThrow(()->  new ValidateException("Không tìm thấy lộ trình"));
        roadMapRepository.delete(roadMapEntity);
    }
}
