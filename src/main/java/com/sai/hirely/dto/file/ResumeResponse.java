package com.sai.hirely.dto.file;

import java.time.LocalDateTime;

public record ResumeResponse(
        Long id,
        String fileName,
        LocalDateTime uploadedAt
) {
}
