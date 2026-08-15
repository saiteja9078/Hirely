package com.sai.hirely.dto.job;

import com.sai.hirely.models.enums.ApplicationStatus;
import com.sai.hirely.models.utils.Location;

import java.time.LocalDateTime;

public record DetailedApplicationResponse(
        Long id,
        ApplicationStatus status,
        LocalDateTime appliedAt,
        String coverLetter,
        Long resumeId,

        Long jobId,
        String jobTitle,
        String jobDescription,
        Location jobLocation,
        String jobWorkMode,
        Integer jobSalaryLower,
        Integer jobSalaryHigher,
        
        Long companyId,
        String companyName,
        
        int totalApplicants
) {
}
