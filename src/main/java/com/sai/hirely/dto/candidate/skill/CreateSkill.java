package com.sai.hirely.dto.candidate.skill;

import com.sai.hirely.models.enums.Proficiency;

public record CreateSkill(
        String name,
        Proficiency proficiency
) {
}
