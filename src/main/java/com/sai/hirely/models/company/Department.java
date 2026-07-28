package com.sai.hirely.models.company;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "hiring_departments")
public class Department
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hiring_department_seq")
    @SequenceGenerator(name = "hiring_department_seq",allocationSize = 50)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "hiringDepartment",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<HiringManager>  hiringManagers;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
