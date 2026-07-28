package com.sai.hirely.models.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sai.hirely.models.utils.Skill;
import com.sai.hirely.models.enums.Proficiency;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(
        name = "candidate_skills",
        indexes = {
                @Index(name = "candidate_skill_idx",columnList = "skill_id,proficiency")
        },
        uniqueConstraints = @UniqueConstraint(
                name = "unique_candidate_skill" ,
                columnNames = {
                        "skill_id",
                        "candidate_id"
                }
        )
)
public class CandidateSkill
{
    @EmbeddedId
    private CandidateSkillKey id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NonNull
    private Proficiency proficiency;

    @ManyToOne
    @MapsId("candidateId") // tells to map this fields pk to provided name in the composite pk;
    @JoinColumn(name = "candidate_id") // creates a fk referring to the fields primary key;
    private Candidate candidate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("skillId")
    private Skill skill;

    public CandidateSkill(){}
    public CandidateSkill(
            Candidate candidate,
            Skill skill,
            Proficiency proficiency
    ) {
        this.id = new CandidateSkillKey(skill.getId(), candidate.getId());
        this.candidate = candidate;
        this.proficiency = Objects.requireNonNull(proficiency);
        this.skill = skill;
    }
}