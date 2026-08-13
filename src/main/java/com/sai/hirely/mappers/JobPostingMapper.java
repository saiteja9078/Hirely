package com.sai.hirely.mappers;


import com.sai.hirely.dto.RoleDto;
import com.sai.hirely.dto.job.JobPostingRequest;
import com.sai.hirely.dto.job.JobPostingResponse;
import com.sai.hirely.dto.skill.job.JobSkillResponse;
import com.sai.hirely.models.job.JobPosting;
import com.sai.hirely.models.job.JobSkillRequirement;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
@Mapper(componentModel = "spring", uses = CompanyMapper.class)
public abstract class JobPostingMapper {

    @Autowired
    protected CompanyMapper companyMapper;

    public JobPostingResponse toResponse(JobPosting posting) {

        List<JobSkillResponse> skills = new ArrayList<>();

        for (JobSkillRequirement requirement : posting.getSkillRequirements()) {
            skills.add(
                    new JobSkillResponse(
                            requirement.getId(),
                            requirement.getSkill().getName(),
                            requirement.getProficiency(),
                            requirement.isRequired()
                    )
            );
        }

        return new JobPostingResponse(
                posting.getId(),
                companyMapper.toResponse(posting.getCompany()),
                skills,
                posting.getTitle(),
                posting.getDescription(),
                posting.getSalaryLower(),
                posting.getSalaryHigher(),
                posting.getMinimumExperienceInMonths(),
                posting.getStatus(),
                posting.getPostedAt(),
                posting.getExpiresAt(),
                posting.getLocation(),
                new RoleDto(
                        posting.getRole().getId(),
                        posting.getRole().getName()
                ),
                posting.getWorkMode(),
                posting.getWorkingHoursPerDay(),
                posting.getType()
        );
    }
}