package com.sai.hirely.models.job;

import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.models.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "job_applications",
        indexes ={ @Index(name = "candidate_index", columnList = "candidate_id") },
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_candidate_id_job_posting_id",
                        columnNames = {"candidate_id", "job_posting_id"})
        }
)
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_application_seq")
    @SequenceGenerator(name = "job_application_seq", allocationSize = 50)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @CreatedDate
    private LocalDateTime appliedAt;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;
}
