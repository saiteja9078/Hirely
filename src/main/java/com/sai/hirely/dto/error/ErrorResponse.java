package com.sai.hirely.dto.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        HttpStatus status,
        String error,
        String message // to let user know
) {
}
