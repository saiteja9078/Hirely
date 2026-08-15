package com.sai.hirely.models.utils;


import com.sai.hirely.models.candidate.Candidate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity(name = "resumes")
@Table
@Getter
@Setter
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private String actualName;

    private String storedPath;

    private LocalDateTime uploadedAt;

    @Column(columnDefinition = "TEXT")
    private String content;

    protected Resume(){}

    public Resume(String actualName,
                  String storedPath,
                  String content,
                  Candidate candidate
                  ) {
        this.actualName = actualName;
        this.storedPath = storedPath;
        this.content = content;
        this.uploadedAt = LocalDateTime.now();
        this.candidate = candidate;
    }
}
