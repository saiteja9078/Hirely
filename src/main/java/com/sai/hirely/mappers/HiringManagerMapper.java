package com.sai.hirely.mappers;

import com.sai.hirely.dto.company.HiringManagerRequest;
import com.sai.hirely.dto.company.HiringManagerResponse;
import com.sai.hirely.models.company.HiringManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface HiringManagerMapper {
    HiringManagerResponse toResponse(HiringManager manager);
    @Mapping(target = "hiringDepartment", ignore = true)
    HiringManager toEntity(HiringManagerRequest request);
    @Mapping(target = "hiringDepartment", ignore = true)
    HiringManager toEntity(com.sai.hirely.dto.auth.HiringManagerSignupRequest request);
}
