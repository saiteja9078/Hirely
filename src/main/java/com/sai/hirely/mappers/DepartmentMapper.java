package com.sai.hirely.mappers;

import com.sai.hirely.dto.company.DepartmentRequest;
import com.sai.hirely.dto.company.DepartmentResponse;
import com.sai.hirely.models.company.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    DepartmentResponse toResponse(Department department);
    
    @Mapping(target = "company", ignore = true)
    Department toEntity(DepartmentRequest request);
}
