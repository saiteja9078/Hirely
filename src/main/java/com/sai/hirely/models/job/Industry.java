package com.sai.hirely.models.job;


import com.sai.hirely.models.company.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(
        name = "industries",
        uniqueConstraints = @UniqueConstraint(name = "unique_dpt_constrain",columnNames = "name")
)
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "industry_seq")
    @SequenceGenerator(name = "industry_seq", allocationSize = 50)
    private Long id;
    private String name;

    public Industry() {}
    public Industry(String name) { this.name = name; }

    @OneToMany(mappedBy = "industry")
    private List<Company> companies;
}
