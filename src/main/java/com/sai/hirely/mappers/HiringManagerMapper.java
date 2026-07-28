package com.sai.hirely.mappers;

import com.sai.hirely.dto.company.HiringManagerRequest;
import com.sai.hirely.dto.company.HiringManagerResponse;
import com.sai.hirely.models.company.HiringManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface HiringManagerMapper {
    HiringManagerResponse toResponse(HiringManager manager);

    @Mapping(target = "hiringDepartment", ignore = true)
    HiringManager toEntity(HiringManagerRequest request);
}
