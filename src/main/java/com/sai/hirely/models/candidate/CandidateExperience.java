package com.sai.hirely.models.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.hirely.models.company.Company;
import com.sai.hirely.models.utils.RoleEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private RoleEntity role;

    private String organizationName;

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
    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    protected CandidateExperience() {

    }
    public CandidateExperience(RoleEntity role,
                               String organizationName,
                               Company company,
                               Candidate candidate,
                               LocalDateTime fromDate,
                               LocalDateTime toDate
                               ) {
        this.role = role;
        this.organizationName = organizationName;
        this.company = company;
        this.fromDate = Objects.requireNonNull(fromDate);
        this.toDate = toDate;
        this.candidate = candidate;
    }
}
