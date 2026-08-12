package com.sai.hirely.dto.company;

import com.sai.hirely.dto.job.IndustryDto;
import com.sai.hirely.models.utils.Location;

public record CompanyResponse(
        Long id,
        String name,
        String companyProfileUrl,
        String email,
        Location location,
        IndustryDto industry
) {
}
