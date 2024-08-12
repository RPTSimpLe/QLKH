package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.CourseConvert;
import com.DoAn.f88.dto.CourseDTO;
import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.exeption.Error403.CheckNullVariable;
import com.DoAn.f88.exeption.Error403.ValidateValueForm;
import com.DoAn.f88.repository.CourseRepository;
import com.DoAn.f88.request.CourseRequest;
import com.DoAn.f88.service.CourseService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseConvert courseConvert;
    @Autowired
    private EntityManager entityManager;

    @Override
    public CourseDTO create(CourseRequest courseRequest) {
        ValidateValueForm.validateNull(courseRequest);

        CourseEntity courseEntity = courseConvert.toEntity(courseRequest);
        courseEntity = courseRepository.save(courseEntity);
        return courseConvert.toDto(courseEntity);
    }

    @Override
    public PageDTO<CourseDTO> getAll(Map<String, String> params) {
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

        StringBuilder selectStringBuilder = new StringBuilder("select r from CourseEntity r where r.isDeleted = false ");
        StringBuilder countBuilder = new StringBuilder("select count(r.id) from CourseEntity r where r.isDeleted = false ");

        if (CheckNullVariable.checkNullString(sCode)){
            selectStringBuilder.append(" and r.sCode like :sCode ");
            countBuilder.append(" and r.sCode like :sCode ");
        }
        if (CheckNullVariable.checkNullString(sName)){
            selectStringBuilder.append(" and r.sName like :sName ");
            countBuilder.append(" and r.sName like :sName ");
        }

        TypedQuery<CourseEntity> selectQuery = entityManager.createQuery(selectStringBuilder.toString(), CourseEntity.class);
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

        List<CourseEntity> courseEntityList = selectQuery.getResultList();
        Long count = countQuery.getSingleResult();

        List<CourseDTO> courseDTOList = courseConvert.toDtoList(courseEntityList);
        return new PageDTO<>(page, limit, count, courseDTOList);
    }
}
