package com.sai.hirely.mappers;

import com.sai.hirely.dto.candidate.CandidateRequest;
import com.sai.hirely.dto.candidate.CandidateResponse;
import com.sai.hirely.models.candidate.Candidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface CandidateMapper {
    CandidateResponse toResponse(Candidate candidate);
    Candidate toEntity(CandidateRequest request);
    Candidate toEntity(com.sai.hirely.dto.auth.CandidateSignupRequest request);
}