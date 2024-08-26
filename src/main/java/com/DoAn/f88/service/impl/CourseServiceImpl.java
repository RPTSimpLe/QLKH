package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.CourseConvert;
import com.DoAn.f88.dto.CourseDTO;
import com.DoAn.f88.dto.PageDTO;
import com.DoAn.f88.entity.CourseEntity;
import com.DoAn.f88.entity.DetailCourseEntity;
import com.DoAn.f88.exeption.Error403.CheckNullVariable;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.exeption.Error403.ValidateValueForm;
import com.DoAn.f88.repository.CourseRepository;
import com.DoAn.f88.repository.DetailCourseRepository;
import com.DoAn.f88.request.CourseRequest;
import com.DoAn.f88.service.CourseService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseConvert courseConvert;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DetailCourseRepository detailCourseRepository;

    @Override
    public CourseDTO create(CourseRequest courseRequest) {
        ValidateValueForm.validateNull(courseRequest);

        CourseEntity courseEntity = courseConvert.toEntity(courseRequest);
        courseEntity = courseRepository.save(courseEntity);
        return courseConvert.toDtoCustom(courseEntity);
    }

    public List<CourseDTO> findAll(){
        List<CourseEntity> courseEntityList = courseRepository.findAll();
        return courseConvert.toDtoList(courseEntityList);
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
            selectStringBuilder.append(" and r.code like :sCode ");
            countBuilder.append(" and r.code like :sCode ");
        }
        if (CheckNullVariable.checkNullString(sName)){
            selectStringBuilder.append(" and r.name like :sName ");
            countBuilder.append(" and r.name like :sName ");
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

    @Override
    public CourseDTO findById(String id) {
        Long courseId = CheckNullVariable.checkValidateLong(id);

        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(() -> new ValidateException("Không tìm thấy khóa học"));
        return  courseConvert.toDtoCustom(courseEntity);
    }

    @Override
    public CourseDTO update(CourseRequest courseRequest, String id) {
        Long courseId = CheckNullVariable.checkValidateLong(id);
        ValidateValueForm.validateNull(courseRequest);
        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(() -> new ValidateException("Không tìm thấy khóa học"));

        CourseEntity newCourseEntity = courseConvert.toEntity(courseRequest);
        courseEntity.setName(newCourseEntity.getName());
        courseEntity.setLevel(newCourseEntity.getLevel());
        courseEntity.setDescription(newCourseEntity.getDescription());
        courseEntity = courseRepository.save(courseEntity);
        return courseConvert.toDtoCustom(courseEntity);
    }

    @Override
    public void delete(String id) {
        Long courseId = CheckNullVariable.checkValidateLong(id);
        CourseEntity courseEntity = courseRepository.findById(courseId).orElseThrow(() -> new ValidateException("Không tìm thấy khóa học"));
        courseEntity.setIsDeleted(true);
        for (DetailCourseEntity detailCourseEntity : courseEntity.getDetailCourse()){
            detailCourseEntity.setIsDeleted(true);
            detailCourseRepository.save(detailCourseEntity);
        }
        courseRepository.save(courseEntity);
    }

    @Override
    public Long calculateTotalPrice(List<Long> ids) {
        long total = 0;
        for (Long id : ids) {
            total+= courseRepository.getPriceById(id);
        }
        return total;
    }
}
