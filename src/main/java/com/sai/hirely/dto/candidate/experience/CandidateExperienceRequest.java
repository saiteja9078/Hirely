package com.sai.hirely.dto.candidate.experience;
import com.sai.hirely.models.candidate.CandidateExperience;
import jakarta.validation.Valid;

import java.util.List;
public record CandidateExperienceRequest(
        Long candidateId,
        List<@Valid ExistingExperienceDto> existingRoles,
        List<@Valid CreateExperienceDto> createRoles
) {

}