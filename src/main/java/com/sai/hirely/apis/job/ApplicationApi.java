package com.sai.hirely.apis.job;

import com.sai.hirely.dto.job.ApplicantResponse;
import com.sai.hirely.dto.job.ApplicationResponse;
import com.sai.hirely.dto.job.ApplicationStatusRequest;
import com.sai.hirely.security.CurrentUser;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;
import com.sai.hirely.service.job.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationApi {
    private final JobApplicationService applicationService;

    public ApplicationApi(JobApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/me")
    public List<ApplicationResponse> getMyApplications(@AuthenticationPrincipal CustomUserDetails user) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return applicationService.findByCandidateId(user.getId());
    }

    @GetMapping("/job/{jobId}")
    public List<ApplicantResponse> getApplicants(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long jobId
    ) {
        CurrentUser.require(user, AccountType.COMPANY, AccountType.HIRING_MANAGER);
        applicationService.assertCanManageJob(jobId, user);
        return applicationService.findApplicants(jobId);
    }

    @PatchMapping("/{applicationId}")
    public ResponseEntity<ApplicationResponse> updateStatus(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long applicationId,
            @Valid @RequestBody ApplicationStatusRequest request
    ) {
        CurrentUser.require(user, AccountType.COMPANY, AccountType.HIRING_MANAGER);
        return ResponseEntity.ok(applicationService.updateStatus(applicationId, request.status(), user));
    }

    @GetMapping("/{applicationId}/resume/download")
    public ResponseEntity<org.springframework.core.io.Resource> downloadApplicationResume(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long applicationId
    ) {
        CurrentUser.require(user, AccountType.COMPANY, AccountType.HIRING_MANAGER);
        return applicationService.downloadResume(applicationId, user);
    }
}
