package com.sai.hirely.repository.skill;

import com.sai.hirely.models.job.JobSkillKey;
import com.sai.hirely.models.job.JobSkillRequirement;
import com.sai.hirely.models.utils.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSkillRequirementRepo extends JpaRepository<JobSkillRequirement, JobSkillKey> {
    Iterable<? extends JobSkillRequirement> skill(Skill skill);
}
