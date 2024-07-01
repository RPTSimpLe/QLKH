package com.DoAn.f88.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "incomeStatics")
@Setter
@Getter
public class IncomeStatic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameStudent;
    private String nameRoadmap;
    private Long price;
}
