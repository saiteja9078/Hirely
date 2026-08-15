package com.sai.hirely.models.job;

import com.sai.hirely.models.company.Company;
import com.sai.hirely.models.company.HiringManager;
import com.sai.hirely.models.utils.JobType;
import com.sai.hirely.models.utils.Location;
import com.sai.hirely.models.utils.RoleEntity;
import com.sai.hirely.models.enums.PostingStatus;
import com.sai.hirely.models.utils.WorkMode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.HashSet;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.Check;

@Entity
@Getter
@Setter
@Check(constraints = "salary_lower <= salary_higher")
@Table(name = "job_postings", indexes = {
        @Index(name = "company_index", columnList = "company_id"),
        @Index(name = "role_index", columnList = "role_id,status"),
        @Index(name = "salary_index", columnList = "salary_higher,salary_lower")
})
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_posting_seq")
    @SequenceGenerator(name = "job_posting_seq", allocationSize = 50)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int salaryLower = 0;
    private int salaryHigher = 0;

    @Enumerated(EnumType.STRING)
    private PostingStatus status;

    @ManyToOne
    @JoinColumn(name = "hiring_manager_id")
    private HiringManager hiringManager;

    @Enumerated(EnumType.STRING)
    private JobType type;

    private Short workingHoursPerDay = 8;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Embedded
    private Location location;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    private WorkMode workMode;

    private Integer minimumExperienceInMonths;

    @CreatedDate
    private LocalDateTime postedAt;
    private LocalDateTime expiresAt;

    @OneToMany(mappedBy = "jobPosting", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<JobSkillRequirement> skillRequirements = new HashSet<>();

    @OneToMany(mappedBy = "jobPosting", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<JobApplication> jobApplications = new HashSet<>();

    public void addSkillRequirement(JobSkillRequirement skillRequirement) {
        this.skillRequirements.add(skillRequirement);
        skillRequirement.setJobPosting(this);
    }

    public void addApplication(JobApplication application) {
        this.jobApplications.add(application);
        application.setJobPosting(this);
    }
}
