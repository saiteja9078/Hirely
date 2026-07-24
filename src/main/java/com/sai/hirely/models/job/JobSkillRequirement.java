package com.sai.hirely.models.job;

import com.sai.hirely.models.enums.Proficiency;
import com.sai.hirely.models.utils.Skill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "job_skills",
        indexes = {
                @Index(name = "job_skill_prof_idx",columnList = "skill_id,proficiency")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_job_skill",columnNames = {"job_posting_id","skill_id"})
        }
)
public class JobSkillRequirement
{
    @EmbeddedId
    private JobSkillKey id;

    @ManyToOne
    @MapsId("jobPostingId")
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Enumerated(EnumType.STRING)
    private Proficiency proficiency;

    protected JobSkillRequirement() {}
    public JobSkillRequirement(
        JobPosting posting,
        Skill skill,
        Proficiency proficiency
    ) {
        this.jobPosting = posting;
        this.skill = skill;
        this.proficiency = proficiency;
        id = new JobSkillKey();
    }
}
