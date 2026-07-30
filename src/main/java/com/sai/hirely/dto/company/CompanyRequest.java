package com.sai.hirely.dto.company;

import com.sai.hirely.models.utils.Location;

public record CompanyRequest(
        String name,
        String companyProfileUrl,
        Location location,
        Long departmentId,
        Long industryId
) {
}
