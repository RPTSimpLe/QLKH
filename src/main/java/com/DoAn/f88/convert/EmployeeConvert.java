package com.DoAn.f88.convert;

import com.DoAn.f88.entity.EmployeeEntity;
import com.DoAn.f88.request.employee.EmployeeRequest;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConvert {
    public EmployeeEntity toEntity(EmployeeRequest employeeRequest, EmployeeEntity employeeEntity) {
        employeeEntity.setCertificate(employeeRequest.getCertificate());
        return employeeEntity;
    }
}
