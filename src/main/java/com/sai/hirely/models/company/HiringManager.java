package com.sai.hirely.models.company;

import com.sai.hirely.models.enums.Gender;
import com.sai.hirely.models.job.JobApplication;
import com.sai.hirely.models.job.JobPosting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "hiring_managers")
public class HiringManager {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hr_seq")
    @SequenceGenerator(name = "hr_seq", allocationSize = 50)
    private Long id;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department hiringDepartment;

    @OneToMany(mappedBy = "hiringManager", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobPosting> jobPostings;
}
