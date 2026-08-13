package com.sai.hirely.dto.company;

import com.sai.hirely.models.enums.Gender;

public record HiringManagerRequest(
        String firstName,
        String lastName,
        Gender gender,
        String email,
        Long departmentId
) {
}
