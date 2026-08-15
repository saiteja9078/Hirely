package com.sai.hirely.apis.candidate;
import com.sai.hirely.dto.candidate.CandidateRequest;
import com.sai.hirely.dto.candidate.CandidateResponse;
import com.sai.hirely.exceptions.candidate.CandidateNotFoundException;
import com.sai.hirely.mappers.CandidateMapper;
import com.sai.hirely.service.candidate.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.sai.hirely.security.CurrentUser;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;

@RestController
@RequestMapping("/api/candidates")
public class CandidateApi
{
    private CandidateService candidateService;
    private CandidateMapper candidateMapper;

    @Autowired
    public CandidateApi(CandidateService candidateService, CandidateMapper candidateMapper) {
        this.candidateService = candidateService;
        this.candidateMapper = candidateMapper;
    }
    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidate(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long id) throws CandidateNotFoundException {
        CurrentUser.require(user, AccountType.CANDIDATE);
        CurrentUser.requireId(user, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(candidateMapper.toResponse(candidateService.findById(id)));
    }

    @GetMapping("/me")
    public ResponseEntity<CandidateResponse> getCurrentCandidate(@AuthenticationPrincipal CustomUserDetails user) throws CandidateNotFoundException {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return ResponseEntity.ok(candidateMapper.toResponse(candidateService.findById(user.getId())));
    }

    @PatchMapping("/me")
    public ResponseEntity<CandidateResponse> updateCurrentCandidate(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody CandidateRequest request
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return ResponseEntity.ok(candidateMapper.toResponse(candidateService.updateCandidate(user.getId(), request)));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteCurrentCandidate(@AuthenticationPrincipal CustomUserDetails user) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        candidateService.deleteCandidate(user.getId());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CandidateResponse> updateCandidate(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long id,
            @RequestBody CandidateRequest request
    )  {
        CurrentUser.require(user, AccountType.CANDIDATE);
        CurrentUser.requireId(user, id);
        return ResponseEntity.status(HttpStatus.OK).body(candidateMapper.toResponse(candidateService.updateCandidate(id,request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long id) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        CurrentUser.requireId(user, id);
        candidateService.deleteCandidate(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
