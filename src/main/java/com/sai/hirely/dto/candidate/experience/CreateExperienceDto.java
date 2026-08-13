package com.sai.hirely.dto.candidate.experience;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateExperienceDto(
       @NotBlank String roleName,
        Long companyId,
        String organizationName,
        String description,
        @NotNull java.time.LocalDateTime fromDate,
        java.time.LocalDateTime toDate
) {

}
