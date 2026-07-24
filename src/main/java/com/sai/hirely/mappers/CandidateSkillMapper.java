package com.sai.hirely.mappers;

import com.sai.hirely.dto.candidate.skill.CandidateSkillResponse;
import com.sai.hirely.dto.candidate.skill.SkillDto;
import com.sai.hirely.models.candidate.CandidateSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel="spring")
public abstract class CandidateSkillMapper {
    @Mapping(source = "skill.name",target = "name")
    @Mapping(source = "id.skillId",target = "id")
    public abstract SkillDto toSkillDto(CandidateSkill sKill);
    public CandidateSkillResponse toResponse(List<CandidateSkill> skillList,Long candidateId) {
        if(skillList==null) return null;
        List<SkillDto> skillDtos = new ArrayList<>();
        for(CandidateSkill skill: skillList) {
            skillDtos.add(
                    new SkillDto(skill.getId().getSkillId(),skill.getSkill().getName(),skill.getProficiency())
            );
        }
        return new CandidateSkillResponse(
                candidateId,skillDtos
        );
    }

}
