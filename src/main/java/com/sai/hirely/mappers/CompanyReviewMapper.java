package com.sai.hirely.mappers;

import com.sai.hirely.dto.company.CompanyReviewResponse;
import com.sai.hirely.models.company.CompanyReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyReviewMapper {
    @Mapping(source = "candidate.id", target = "candidateId")
    @Mapping(source = "company.id", target = "companyId")
    CompanyReviewResponse toResponse(CompanyReview review);

    List<CompanyReviewResponse> toResponseList(List<CompanyReview> reviews);
}
