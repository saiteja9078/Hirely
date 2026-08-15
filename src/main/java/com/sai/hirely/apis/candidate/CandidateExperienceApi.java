package com.sai.hirely.apis.candidate;

import com.sai.hirely.dto.candidate.experience.CandidateExperienceRequest;
import com.sai.hirely.dto.candidate.experience.CandidateExperienceResponse;
import com.sai.hirely.dto.candidate.experience.CandidateExperienceUpdateRequest;
import com.sai.hirely.service.candidate.CandidateExperienceService;
import com.sai.hirely.service.candidate.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.sai.hirely.security.CurrentUser;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;
import java.util.List;

@RestController
@RequestMapping("/api/candidate-experiences")
public class CandidateExperienceApi {
    private final CandidateService candidateService;
    private final CandidateExperienceService candidateExperienceService;

    public CandidateExperienceApi(CandidateService candidateService, CandidateExperienceService candidateExperienceService) {
        this.candidateService = candidateService;
        this.candidateExperienceService = candidateExperienceService;
    }

    @GetMapping("/me")
    public ResponseEntity<List<CandidateExperienceResponse>> findCurrentCandidateExperiences(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return ResponseEntity.ok(candidateExperienceService.findById(user.getId()));
    }

    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCurrentCandidateExperiences(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody CandidateExperienceRequest experienceRequest
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        candidateExperienceService.addCandidateExperiences(new CandidateExperienceRequest(
                user.getId(), experienceRequest.existingRoles(), experienceRequest.createRoles()));
    }

    @PatchMapping("/me")
    public CandidateExperienceResponse updateCurrentCandidateExperience(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody CandidateExperienceUpdateRequest experienceRequest
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return candidateExperienceService.updateExperience(user.getId(), experienceRequest);
    }

    @DeleteMapping("/me/{experienceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCurrentCandidateExperience(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long experienceId
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        candidateExperienceService.deleteExperience(experienceId, user.getId());
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<List<CandidateExperienceResponse>> findByCandidateId(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long candidateId
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        CurrentUser.requireId(user, candidateId);
        return ResponseEntity.status(HttpStatus.OK).body(candidateExperienceService.findById(candidateId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCandidateExperiences(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody CandidateExperienceRequest experienceRequest
            ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        CurrentUser.requireId(user, experienceRequest.candidateId());
        candidateExperienceService.addCandidateExperiences(experienceRequest);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CandidateExperienceResponse updateCandidateExperience(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody CandidateExperienceUpdateRequest experienceRequest
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return candidateExperienceService.updateExperience(user.getId(), experienceRequest);
    }

    @DeleteMapping("/{experienceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateExperience(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long experienceId) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        candidateExperienceService.deleteExperience(experienceId, user.getId());
    }
}
