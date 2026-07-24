package com.sai.hirely.models.job;

import com.sai.hirely.models.company.Company;
import com.sai.hirely.models.company.HiringManager;
import com.sai.hirely.models.utils.Role;
import com.sai.hirely.models.enums.PostingStatus;
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
@Table(
        name = "job_postings",
        indexes = {
                @Index(name = "company_index", columnList = "company_id"),
                @Index(name = "role_index", columnList = "role_id,status"),
                @Index(name = "salary_index", columnList = "salary_higher,salary_lower")
        }
)
public class JobPosting
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "job_posting_seq")
    @SequenceGenerator(name = "job_posting_seq",allocationSize = 50)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer salaryLower;
    private Integer salaryHigher;

    @Enumerated(EnumType.STRING)
    private PostingStatus status;

    @ManyToOne
    @JoinColumn(name = "hiring_manager_id")
    private HiringManager hiringManager;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private String location;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @CreatedDate
    private LocalDateTime postedAt;
    private LocalDateTime expiresAt;

    @OneToMany(mappedBy = "jobPosting", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<JobSkillRequirement> skillRequirements = new HashSet<>();

    @OneToMany(mappedBy = "jobPosting", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<JobExperienceRequirement> experienceRequirements = new HashSet<>();

    @OneToMany(mappedBy = "jobPosting", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<JobApplication> jobApplications = new HashSet<>();

    public void addSkillRequirement(JobSkillRequirement skillRequirement) {
        this.skillRequirements.add(skillRequirement);
        skillRequirement.setJobPosting(this);
    }
    public void addExperienceRequirement(JobExperienceRequirement experienceRequirement) {
        this.experienceRequirements.add(experienceRequirement);
        experienceRequirement.setJobPosting(this);
    }
    public void addApplication(JobApplication application) {
        this.jobApplications.add(application);
        application.setJobPosting(this);
    }
}
