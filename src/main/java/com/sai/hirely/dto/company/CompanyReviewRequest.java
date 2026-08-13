package com.sai.hirely.dto.company;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CompanyReviewRequest(
        @NotBlank String text,
        @NotNull @Min(1) @Max(5) Short stars
) {
}
