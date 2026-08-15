package com.sai.hirely.dto.auth;

import com.sai.hirely.models.utils.Location;

public record CompanySignupRequest(
        String name,
        String companyProfileUrl,
        String email,
        String password,
        Location location,
        Long departmentId,
        Long industryId,
        String industryName
) {
}
