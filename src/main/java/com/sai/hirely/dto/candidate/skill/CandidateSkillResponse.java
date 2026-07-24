package com.sai.hirely.dto.candidate.skill;


import java.util.List;
public record CandidateSkillResponse(
        Long candidateId,
        List<SkillDto> skillList
) {
}
