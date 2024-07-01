package com.DoAn.f88.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "students")
public class StudentEntity extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String education;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity   account;

    @OneToMany(mappedBy = "student")
    private List<RoadMapEntity> roadMap;
}
