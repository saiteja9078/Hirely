package com.sai.hirely.dto.company;

import com.sai.hirely.models.enums.Gender;

public record HiringManagerResponse(
        Long id,
        String firstName,
        String lastName,
        Gender gender,
        String email,
        DepartmentResponse hiringDepartment
) {
}
