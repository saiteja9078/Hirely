package com.sai.hirely.dto.candidate.experience;

import jakarta.validation.constraints.NotNull;

public record CandidateExperienceResponse(
        Long experienceId,
        Long roleId,
        String roleName,
        String organizationName,
        Long companyId,
        String companyName,
        String description,
        Short experienceInMonths
) {
}