package com.sai.hirely.apis.job;

import com.sai.hirely.service.job.JobApplicationService;
import com.sai.hirely.security.CurrentUser;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/apply")
public class JobApplicationApi {

    private final JobApplicationService applicationService;
    @Autowired
    public JobApplicationApi(JobApplicationService service) {
        this.applicationService = service;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void apply(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestParam Long jobId,
            @RequestParam(required = false) String coverLetter,
            @RequestParam(required = false) Long resumeId,
            @RequestParam(required = false, defaultValue = "false") boolean alerts
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        applicationService.apply(jobId, user.getId(), coverLetter, resumeId, alerts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobApplication(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long id
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        applicationService.deleteJobApplication(id, user.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public com.sai.hirely.dto.job.DetailedApplicationResponse getApplicationDetails(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long id
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return applicationService.getApplicationDetails(id, user.getId());
    }

//    @GetMapping("/findAll/{jobId}")
//    public Page findAllCandidates(
//            @PathVariable Long jobId
//    ) {
////        return applicationService.findCandidates(jobId);
//        return null;
//    }
}
