package com.sai.hirely.dto;

import java.time.LocalDateTime;

public record NotificationResponse(
        String id,
        String title,
        String body,
        LocalDateTime createdAt
) {
}
