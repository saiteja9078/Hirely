package com.sai.hirely.dto.company;

import java.time.LocalDateTime;

public record CompanyReviewResponse(
        Long id,
        String text,
        Short stars,
        Long candidateId,
        String candidateName,
        Long companyId,
        LocalDateTime createdAt
) {
}
