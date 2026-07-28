package com.sai.hirely.dto.candidate.skill;

import jakarta.validation.constraints.NotNull;


import java.util.List;
public record CandidateSkillsRequest(
         @NotNull Long candidateId,
         List<ExistingSkill> addExistingSkills,
         List<CreateSkill> createNewSkills
){
}
