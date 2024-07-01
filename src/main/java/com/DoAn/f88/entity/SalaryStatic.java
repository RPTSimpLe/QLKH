package com.DoAn.f88.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "salaryStatic")
@Getter
@Setter
public class SalaryStatic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameTeacher;
    private Date dateOfBirth;
    private String phone;
    private Integer numberPreiod;
    private Long salary;
}
