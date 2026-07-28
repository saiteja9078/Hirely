package com.sai.hirely.mappers;

import com.sai.hirely.dto.candidate.experience.CandidateExperienceResponse;
import com.sai.hirely.models.candidate.CandidateExperience;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateExperienceMapper {
    @Mapping(source = "id", target = "experienceId")
    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "role.name", target = "roleName")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.name", target = "companyName")
    CandidateExperienceResponse toResponse(CandidateExperience experience);

    List<CandidateExperienceResponse> toResponseList(List<CandidateExperience> experiences);
}
