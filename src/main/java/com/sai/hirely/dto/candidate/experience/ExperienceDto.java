package com.sai.hirely.dto.candidate.experience;

public record ExperienceDto(
        Long roleId,
        String organizationName,
        Long companyId,
        String description,
        Short experienceInMonths
) {
}
