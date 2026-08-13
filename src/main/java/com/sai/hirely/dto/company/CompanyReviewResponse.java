package com.sai.hirely.dto.company;

import java.time.LocalDateTime;

public record CompanyReviewResponse(
        Long id,
        String text,
        Short stars,
        Long candidateId,
        Long companyId,
        LocalDateTime createdAt
) {
}
