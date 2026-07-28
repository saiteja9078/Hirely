package com.sai.hirely.mappers;

import com.sai.hirely.dto.company.CompanyRequest;
import com.sai.hirely.dto.company.CompanyResponse;
import com.sai.hirely.models.company.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyResponse toResponse(Company company);
    Company toEntity(CompanyRequest request);
}
