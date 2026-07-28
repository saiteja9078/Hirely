package com.sai.hirely.mappers;

import com.sai.hirely.dto.candidate.skill.CandidateSkillResponse;
import com.sai.hirely.dto.candidate.skill.CandidateSkillsProjection;
import com.sai.hirely.dto.candidate.skill.SkillDto;
import com.sai.hirely.models.candidate.CandidateSkill;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
@Mapper
public abstract class CandidateSkillsMapper {

    public CandidateSkillResponse toSkillResponse(List<CandidateSkillsProjection> skillsProjections,Long candidateId) {
        List<SkillDto> skillDtos = new ArrayList<>();
        for(CandidateSkillsProjection proj: skillsProjections) {
            skillDtos.add(new SkillDto(
                    proj.skillId(),
                    proj.name(),
                    proj.proficiency()
            ));
        }
        return new CandidateSkillResponse(
                candidateId,skillDtos
        );
    }
    public CandidateSkillResponse toSkillResponse(List<CandidateSkill> candidateSkills,Long candidateId) {
        List<SkillDto> skillDtos = new ArrayList<>();
        for(CandidateSkill cs: candidateSkills){
            skillDtos.add( new SkillDto(cs.getId().getSkillId(),cs.))
        }
        CandidateSkillResponse response = new CandidateSkillResponse();
    }
}
