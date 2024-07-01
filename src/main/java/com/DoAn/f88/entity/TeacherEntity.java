package com.DoAn.f88.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "teachers")
public class TeacherEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Certificate;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity   account;

    @OneToMany(mappedBy = "teacher")
    private List<TimekeepingEntity> timekeepingEntity;
}
