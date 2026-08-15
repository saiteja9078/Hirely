package com.sai.hirely.dto.job;

import com.sai.hirely.dto.skill.candidate.CandidateSkillDto;
import com.sai.hirely.dto.candidate.experience.CandidateExperienceResponse;
import com.sai.hirely.models.enums.ApplicationStatus;
import com.sai.hirely.models.utils.Location;

import java.time.LocalDateTime;
import java.util.List;

public record ApplicantResponse(
        Long applicationId,
        Long candidateId,
        String firstName,
        String lastName,
        String email,
        String description,
        Location location,
        List<CandidateSkillDto> skills,
        List<CandidateExperienceResponse> experiences,
        ApplicationStatus status,
        LocalDateTime appliedAt,
        String coverLetter,
        Long resumeId,
        String resumeName
) {
}
