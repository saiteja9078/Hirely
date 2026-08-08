package com.sai.hirely.dto.job;
import com.sai.hirely.dto.skill.job.JobCreateSkill;
import com.sai.hirely.dto.skill.job.JobExistingSkill;
import com.sai.hirely.models.enums.PostingStatus;
import com.sai.hirely.models.utils.Location;

import java.time.LocalDateTime;
import java.util.List;
public record JobPostingRequest(
        Long hiringManagerId,
        Long companyId,
        Long roleId,
        String title,
        String description,
        Integer salaryLower,
        Integer salaryHigher,
        Integer minimumExperienceInMonths,
        PostingStatus status,
        LocalDateTime postedAt,
        LocalDateTime expiresAt,
        List<JobExistingSkill> existingSkills,
        List<JobCreateSkill> createSkills,
        Location location,
        com.sai.hirely.models.utils.WorkMode workMode
) {
}
