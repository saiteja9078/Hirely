package com.sai.hirely.dto.candidate.experience;

public record CandidateExperienceUpdateRequest(
        Long experienceId,
        Long companyId,
        String organizationName,
        String description,
        Short experienceInMonths
) {
}
