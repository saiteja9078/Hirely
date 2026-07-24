package com.sai.hirely.dto.candidate.skill;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


import java.util.List;
public record CandidateSkillsRequest(
         @NotNull Long candidateId,
        @NotNull
        List<@Valid SkillDto> skillRequestList
){
}
