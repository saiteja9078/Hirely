package com.sai.hirely.dto.job;

import com.sai.hirely.dto.RoleDto;
import com.sai.hirely.dto.company.CompanyResponse;
import com.sai.hirely.dto.skill.job.JobCreateSkill;
import com.sai.hirely.dto.skill.job.JobExistingSkill;
import com.sai.hirely.dto.skill.job.JobSkillResponse;
import com.sai.hirely.models.enums.PostingStatus;
import com.sai.hirely.models.utils.Location;
import com.sai.hirely.models.utils.RoleEntity;

import java.time.LocalDateTime;
import java.util.List;

public record JobPostingResponse(
        Long id,
        CompanyResponse companyResponse,
        List<JobSkillResponse> jobSkillRequirements,
        String title,
        String description,
        Integer salaryLower,
        Integer salaryHigher,
        Integer minimumExperienceInMonths,
        PostingStatus status,
        LocalDateTime postedAt,
        LocalDateTime expiresAt,
        Location location,
        RoleDto role,
        com.sai.hirely.models.utils.WorkMode workMode
) {


}
