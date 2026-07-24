package com.sai.hirely.models.company;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "company_seq")
    @SequenceGenerator(name = "company_seq",allocationSize = 50)
    private Long id;

    @Column(length = 70)
    private String name;

    private String companyProfileUrl;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Department> departments;
}
