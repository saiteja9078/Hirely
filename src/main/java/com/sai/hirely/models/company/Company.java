package com.sai.hirely.models.company;

import com.sai.hirely.models.job.Industry;
import com.sai.hirely.models.utils.Location;
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

    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @Embedded
    private Location location;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Department> departments;
}
