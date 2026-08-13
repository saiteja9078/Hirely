package com.sai.hirely.dto.auth;

import com.sai.hirely.models.enums.Gender;

public record HiringManagerSignupRequest(
        String firstName,
        String lastName,
        Gender gender,
        String email,
        String password,
        Long departmentId
) {
}
