package com.sai.hirely.apis.candidate;

import com.sai.hirely.dto.candidate.experience.CandidateExperienceRequest;
import com.sai.hirely.dto.candidate.experience.CandidateExperienceResponse;
import com.sai.hirely.dto.candidate.experience.CandidateExperienceUpdateRequest;
import com.sai.hirely.service.candidate.CandidateExperienceService;
import com.sai.hirely.service.candidate.CandidateService;
import jakarta.servlet.annotation.HttpConstraint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/candidate-experiences")
public class CandidateExperienceApi {
    private final CandidateService candidateService;
    private final CandidateExperienceService candidateExperienceService;

    public CandidateExperienceApi(CandidateService candidateService, CandidateExperienceService candidateExperienceService) {
        this.candidateService = candidateService;
        this.candidateExperienceService = candidateExperienceService;
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<List<CandidateExperienceResponse>> findByCandidateId(
            @PathVariable Long candidateId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(candidateExperienceService.findById(candidateId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCandidateExperiences(
            @RequestBody CandidateExperienceRequest experienceRequest
            ) {
        candidateExperienceService.addCandidateExperiences(experienceRequest);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CandidateExperienceResponse updateCandidateExperience(
            @RequestBody CandidateExperienceUpdateRequest experienceRequest
    ) {
        return candidateExperienceService.updateExperience(experienceRequest);
    }

    @DeleteMapping("/{experienceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateExperience(@PathVariable Long experienceId) {
        candidateExperienceService.deleteExperience(experienceId);
    }
}
