package com.sai.hirely.dto.skill.candidate;

import com.sai.hirely.dto.skill.CreateSkill;
import com.sai.hirely.dto.skill.ExistingSkill;

import java.util.List;

public record CandidateSkillsPayload(
        List<ExistingSkill> addExistingSkills,
        List<CreateSkill> createNewSkills
) {
}
