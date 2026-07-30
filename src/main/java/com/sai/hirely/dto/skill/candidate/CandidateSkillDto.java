package com.sai.hirely.dto.skill.candidate;

import com.sai.hirely.models.enums.Proficiency;

public record CandidateSkillDto (
        Long id,
        String name,
        Proficiency proficiency
){
}
