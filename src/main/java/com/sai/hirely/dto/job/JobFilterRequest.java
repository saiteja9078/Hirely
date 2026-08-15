package com.sai.hirely.dto.job;

import com.sai.hirely.models.utils.Location;
import com.sai.hirely.models.utils.WorkMode;
import com.sai.hirely.models.utils.JobType;

import java.time.LocalDateTime;
import java.util.List;
public record JobFilterRequest(
        Long roleId,
        String title,
        WorkMode workMode,
        List<JobType> types,

        Integer salaryGe,
        Integer salaryLe,

        LocalDateTime postedAfter,

        String country,
        String state,
        String city,
        String location,

        // implement radius search later
        // Double latitude,
        // Double longitude,
        // Double radiusKm,

        List<Long> companyIds,
        List<Long> skillIds,

        JobSortField sortField,
        SortOrder sortOrder
) {}
