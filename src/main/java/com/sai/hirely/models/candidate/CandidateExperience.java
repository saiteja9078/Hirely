package com.sai.hirely.models.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.hirely.models.company.Company;
import com.sai.hirely.models.utils.Role;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(
        name = "candidate_experiences"
)
public class CandidateExperience
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "candidate_exp_seq"
    )
    @SequenceGenerator(name = "candidate_exp_seq",allocationSize = 50)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(nullable = false)
    @Nonnull
    private Short experienceInMonths;

    protected CandidateExperience() {}
    public CandidateExperience(Role role,Company company,Candidate candidate,Short experienceInMonths) {
        this.role = role;
        this.company = company;
        this.experienceInMonths = Objects.requireNonNull(experienceInMonths);
        this.candidate = candidate;
    }
}
