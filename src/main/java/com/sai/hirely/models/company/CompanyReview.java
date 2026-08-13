package com.sai.hirely.models.company;

import com.sai.hirely.models.candidate.Candidate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "company_reviews",
        indexes = {
                @Index(name = "review_company_index", columnList = "company_id"),
                @Index(name = "review_candidate_index", columnList = "candidate_id")
        }
)
@EntityListeners(AuditingEntityListener.class)
public class CompanyReview {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_review_seq")
    @SequenceGenerator(name = "company_review_seq", allocationSize = 50)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Short stars; // out of 5

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @CreatedDate
    private LocalDateTime createdAt;
}
