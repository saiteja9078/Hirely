package com.sai.hirely.dto.job;

import com.sai.hirely.models.enums.ApplicationStatus;

import java.time.LocalDateTime;

public record ApplicationResponse(
        Long id,
        Long jobId,
        String jobTitle,
        Long companyId,
        String companyName,
        ApplicationStatus status,
        LocalDateTime appliedAt,
        String coverLetter
) {
}
