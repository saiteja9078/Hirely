package com.sai.hirely.dto.job;

import com.sai.hirely.models.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public record ApplicationStatusRequest(@NotNull ApplicationStatus status) {
}
