package com.sai.hirely.dto.skill.job;

import com.sai.hirely.models.enums.Proficiency;
import com.sai.hirely.models.job.JobSkillKey;
import com.sai.hirely.models.job.JobSkillRequirement;

public record JobSkillResponse(
        JobSkillKey skillKey,
        String name,
        Proficiency proficiency,
        boolean required
) {

}
