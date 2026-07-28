package com.sai.hirely.dto.candidate.skill;

import com.sai.hirely.models.enums.Proficiency;

public record CandidateSkillsProjection(
        Long candidateId,
        Long skillId,
        String name,
        Proficiency proficiency
) {
}
