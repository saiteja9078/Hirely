package com.sai.hirely.models.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.hirely.models.enums.Gender;
import com.sai.hirely.models.job.JobApplication;
import com.sai.hirely.models.utils.Location;
import com.sai.hirely.models.utils.RoleEntity;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(
    name = "candidates"
)
public class Candidate
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "candidate_seq"
    )
    @SequenceGenerator(name = "candidate_seq",allocationSize = 50)
    private Long id;

    @Column(nullable = false)
    @Nonnull
    private String firstName;

    @Column(nullable = false)
    @NonNull
    private String lastName;

    private Integer age;

    @Column(length = 512)
    private String profilePictureUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Column(unique = true,nullable = false)
    @Nonnull
    private String email;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Embedded
    private Location location;


    // have to implement this
//    @OneToOne
//    @JoinColumn(name = "desired_role_id")
//    private RoleEntity desiredJobRole;

//    @OneToOne
//    @JoinColumn(name = "current_role_id")
//    private RoleEntity currentRole;





    @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Set<CandidateSkill> candidateSkills = new HashSet<>();

    @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CandidateExperience> candidateExperiences = new HashSet<>();

    @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Set<JobApplication> jobApplications = new HashSet<>();
    protected Candidate() {}

    public Candidate(
        String firstName,
        String lastName,
        Gender gender,
        String password,
        String email,
        String description
    ) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.gender = gender;
        this.password = Objects.requireNonNull(password);
        this.email = Objects.requireNonNull(email);
        this.description = description;
    }

    public void addCandidateSkill(CandidateSkill candidateSkill) {
        this.candidateSkills.add(candidateSkill);
        candidateSkill.setCandidate(this);
    }
    public void addJobApplication(JobApplication jobApplication) {
        this.jobApplications.add(jobApplication);
        jobApplication.setCandidate(this);
    }
    public void addCandidateExperience(CandidateExperience candidateExperience) {
        this.candidateExperiences.add(candidateExperience);
        candidateExperience.setCandidate(this);
    }
}
