package com.sai.hirely.apis.candidate;

import com.sai.hirely.dto.skill.candidate.CandidateSkillDto;
import com.sai.hirely.dto.skill.candidate.CandidateSkillsRequest;
import com.sai.hirely.dto.skill.candidate.CandidateSkillsPayload;
import com.sai.hirely.models.candidate.CandidateSkillKey;
import com.sai.hirely.models.enums.Proficiency;
import com.sai.hirely.service.candidate.CandidateSkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.sai.hirely.security.CurrentUser;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;

import java.util.*;

@RestController
@RequestMapping("/api/candidate-skills")
public class CandidateSkillApi {
    private CandidateSkillService skillService;
    @Autowired
    public CandidateSkillApi(CandidateSkillService skillService) {
        this.skillService = skillService;
    }
    @GetMapping("/me")
    public ResponseEntity<List<CandidateSkillDto>> getCurrentSkills(@AuthenticationPrincipal CustomUserDetails user) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return ResponseEntity.ok(skillService.findAllByCandidateId(user.getId()));
    }

    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCurrentSkills(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody CandidateSkillsPayload payload
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        skillService.addSkills(new CandidateSkillsRequest(user.getId(), payload.addExistingSkills(), payload.createNewSkills()));
    }

    @PatchMapping("/me/{skillId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCurrentSkill(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long skillId,
            @RequestParam Proficiency proficiency
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        skillService.updateSkill(new CandidateSkillKey(skillId, user.getId()), proficiency);
    }

    @DeleteMapping("/me/{skillId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCurrentSkill(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long skillId) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        skillService.deleteSkill(new CandidateSkillKey(skillId, user.getId()));
    }
    @GetMapping("/{candidateId}")
    public ResponseEntity<List<CandidateSkillDto>> getSkills(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long candidateId)  {
        CurrentUser.require(user, AccountType.CANDIDATE);
        CurrentUser.requireId(user, candidateId);
        return ResponseEntity.status(HttpStatus.OK).body(skillService.findAllByCandidateId(candidateId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addSkills(@AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody CandidateSkillsRequest skillsRequest) {
         CurrentUser.require(user, AccountType.CANDIDATE);
         CurrentUser.requireId(user, skillsRequest.candidateId());
         skillService.addSkills(skillsRequest);
    }
    @PatchMapping("/{candidateId}/{skillId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSkill(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long skillId,@PathVariable Long candidateId,
                            @RequestParam Proficiency proficiency
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        CurrentUser.requireId(user, candidateId);
        skillService.updateSkill(new CandidateSkillKey(skillId,candidateId),proficiency);
    }

    @DeleteMapping("/{candidateId}/{skillId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSkill(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long candidateId, @PathVariable Long skillId) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        CurrentUser.requireId(user, candidateId);
        skillService.deleteSkill(new CandidateSkillKey(skillId, candidateId));
    }
}
