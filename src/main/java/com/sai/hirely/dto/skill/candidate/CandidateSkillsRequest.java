package com.sai.hirely.dto.skill.candidate;

import com.sai.hirely.dto.skill.CreateSkill;
import com.sai.hirely.dto.skill.ExistingSkill;
import jakarta.validation.constraints.NotNull;


import java.util.List;
public record CandidateSkillsRequest(
         @NotNull Long candidateId,
         List<ExistingSkill> addExistingSkills,
         List<CreateSkill> createNewSkills
){
}
