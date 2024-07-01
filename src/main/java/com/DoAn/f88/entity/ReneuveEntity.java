package com.DoAn.f88.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reneuves")
@Getter
@Setter
public class ReneuveEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long incomeStatic;
    private Long salaryTeacher;
    private Long reneuve;
}
