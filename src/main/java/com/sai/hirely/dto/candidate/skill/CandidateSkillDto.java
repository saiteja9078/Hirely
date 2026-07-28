package com.sai.hirely.dto.candidate.skill;

import com.sai.hirely.models.enums.Proficiency;

public record CandidateSkillDto (
        Long id,
        String name,
        Proficiency proficiency
){
}
