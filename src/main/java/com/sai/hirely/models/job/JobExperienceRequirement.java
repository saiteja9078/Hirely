package com.sai.hirely.models.job;


import com.sai.hirely.models.utils.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "job_experiences",
        indexes = {@Index(name = "job_id_iindex",columnList = "role_id,experience_in_months")},
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_job_exp",columnNames = {"job_posting_id","role_id"})
        }
)
public class JobExperienceRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "job_experience_requirement_seq")
    @SequenceGenerator(name = "job_experience_requirement_seq",allocationSize = 50)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private Short experienceInMonths;
}
