package com.sai.hirely.dto.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        String message // to let user know
) {
    public ErrorResponse(String message) {
        this(
                LocalDateTime.now(),
                message
        );
    }
}
