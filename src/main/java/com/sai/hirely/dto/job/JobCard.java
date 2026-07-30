package com.sai.hirely.dto.job;

import com.sai.hirely.models.utils.WorkMode;

import java.time.LocalDateTime;

public record JobCard(
        Long id,
        String title,
        Integer salaryLower,
        Integer salaryHigher,
        LocalDateTime postedAt,
        WorkMode workMode,
        Integer minimumExperienceInMonths,
        String companyProfileUrl,
        String companyName
) {}
