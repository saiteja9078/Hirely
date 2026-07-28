package com.sai.hirely.dto.candidate.experience;
import java.util.List;
public record CandidateExperienceRequest(
        Long candidateId,
        List<ExperienceDto> experienceList
)
{}