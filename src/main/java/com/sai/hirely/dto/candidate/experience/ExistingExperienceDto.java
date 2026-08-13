package com.sai.hirely.dto.candidate.experience;

import jakarta.validation.constraints.NotNull;

public record ExistingExperienceDto(
        @NotNull Long roleId,
        Long companyId,
        String organizationName,
        @NotNull java.time.LocalDateTime fromDate,
        java.time.LocalDateTime toDate,
        @NotNull String description
) {

}
