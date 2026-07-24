package com.sai.hirely.dto.candidate.skill;

import com.sai.hirely.models.enums.Proficiency;
import jakarta.validation.constraints.NotBlank;

public record SkillDto(
        Long id,
        @NotBlank
        String name,
        Proficiency proficiency
) {
}
