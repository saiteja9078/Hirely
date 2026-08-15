package com.sai.hirely.apis.login;

import com.sai.hirely.dto.auth.AuthenticationRequest;
import com.sai.hirely.dto.auth.AuthenticationResponse;
import com.sai.hirely.security.JwtService;
import com.sai.hirely.security.details.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CandidateLoginApi {
    private final JwtService jwtService;
    private final DaoAuthenticationProvider authProvider;

    private final com.sai.hirely.service.candidate.CandidateService candidateService;
    private final com.sai.hirely.service.candidate.CandidateSkillService candidateSkillService;
    private final com.sai.hirely.mappers.CandidateMapper candidateMapper;

    public CandidateLoginApi(JwtService jwtService,
            @Qualifier("candidateDetailsService") UserDetailsService userDetailsService,
            @Qualifier("candidateAuthenticationProvider") DaoAuthenticationProvider authManager,
            com.sai.hirely.service.candidate.CandidateService candidateService,
            com.sai.hirely.service.candidate.CandidateSkillService candidateSkillService,
            com.sai.hirely.mappers.CandidateMapper candidateMapper) {
        this.jwtService = jwtService;
        this.authProvider = authManager;
        this.candidateService = candidateService;
        this.candidateSkillService = candidateSkillService;
        this.candidateMapper = candidateMapper;
    }

    @PostMapping("/login/candidate")
    public ResponseEntity<AuthenticationResponse> candidateLogin(
            @Valid @RequestBody AuthenticationRequest request) {
        Authentication auth = authProvider.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(), request.password()));
        String token = jwtService.generateToken((CustomUserDetails) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(token, request.username()));
    }

    @PostMapping("/signup/candidate")
    public ResponseEntity<AuthenticationResponse> candidateSignUp(
            @RequestBody com.sai.hirely.dto.auth.CandidateSignupRequest request) {
        com.sai.hirely.models.candidate.Candidate savedCandidate = candidateService.addCandidate(candidateMapper.toEntity(request));
        candidateSkillService.addExistingSkillIds(savedCandidate.getId(), request.skillsList());
        CustomUserDetails userDetails = new CustomUserDetails(
                savedCandidate.getId(),
                savedCandidate.getEmail(),
                savedCandidate.getPassword(),
                java.util.Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_CANDIDATE")),
                com.sai.hirely.security.details.AccountType.CANDIDATE
        );
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthenticationResponse(token, savedCandidate.getEmail()));
    }
}
