package com.sai.hirely.models.job;

import com.sai.hirely.models.company.HiringManager;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import org.hibernate.boot.model.naming.ImplicitPrimaryKeyJoinColumnNameSource;

import java.time.LocalDateTime;


@Entity
@Table(name = "job_rounds")
@Check(constraints = "rating BETWEEN 1 AND 10")
public class JobRound
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "job_round_seq")
    @SequenceGenerator(name = "job_round_seq",allocationSize = 50)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    private Short roundNumber;
    private String roundName;

    private Byte rating;
    private LocalDateTime at;

    @ManyToOne
    @JoinColumn(name = "hr_id")
    private HiringManager hiringManager;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobApplication application;
}
