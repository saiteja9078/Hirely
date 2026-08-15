package com.sai.hirely.apis.job;

import com.sai.hirely.dto.job.JobCard;
import com.sai.hirely.dto.job.JobFilterRequest;
import com.sai.hirely.dto.job.JobPostingRequest;
import com.sai.hirely.dto.job.JobPostingResponse;
import com.sai.hirely.mappers.JobPostingMapper;
import com.sai.hirely.service.job.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.sai.hirely.security.CurrentUser;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;
import com.sai.hirely.repository.company.HiringManagerRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/post-job")
public class JobApi {
    private final JobPostingMapper jobPostingMapper;
    private final JobPostingService postingService;
    private final HiringManagerRepo hiringManagerRepo;

    @Autowired
    public JobApi(JobPostingService postingService, JobPostingMapper jobPostingMapper, HiringManagerRepo hiringManagerRepo) {
        this.postingService = postingService;
        this.jobPostingMapper = jobPostingMapper;
        this.hiringManagerRepo = hiringManagerRepo;
    }

    @PostMapping
    // redirect to the application page and show them
    public ResponseEntity<JobPostingResponse> createJobPosting(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody JobPostingRequest request
            ) {
        CurrentUser.require(user, AccountType.COMPANY, AccountType.HIRING_MANAGER);
        Long companyId = user.getType() == AccountType.COMPANY ? user.getId() : hiringManagerRepo.findWithCompanyById(user.getId())
                .orElseThrow(() -> new com.sai.hirely.exceptions.company.EntityNotFoundException("HiringManager", user.getId()))
                .getHiringDepartment().getCompany().getId();
        Long hiringManagerId = user.getType() == AccountType.HIRING_MANAGER ? user.getId() : null;
        ResponseEntity<JobPostingResponse> jobPosting =  ResponseEntity.status(HttpStatus.OK)
                .body(jobPostingMapper.toResponse(postingService.createJobPosting(request, companyId, hiringManagerId)));
        return jobPosting;
    }
    @GetMapping("/mine")
    public List<JobPostingResponse> getMyJobs(@AuthenticationPrincipal CustomUserDetails user) {
        CurrentUser.require(user, AccountType.COMPANY, AccountType.HIRING_MANAGER);
        List<com.sai.hirely.models.job.JobPosting> jobs = user.getType() == AccountType.COMPANY
                ? postingService.findByCompanyId(user.getId())
                : postingService.findByHiringManagerId(user.getId());
        return jobs.stream().map(jobPostingMapper::toResponse).toList();
    }
    @GetMapping("/{jobId}")
    public ResponseEntity<JobPostingResponse> findById(
            @PathVariable Long jobId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(jobPostingMapper.toResponse(postingService.findFullJobDetails(jobId)));
    }
    @GetMapping("/filter")
    public Page<JobCard> getPage(
            @RequestBody JobFilterRequest filterRequest,
            Pageable pageable
            ) {
        return postingService.getPage(filterRequest,pageable);
    }

    @PostMapping("/filter")
    public Page<JobCard> postFilter(@RequestBody JobFilterRequest filterRequest, Pageable pageable) {
        return postingService.getPage(filterRequest, pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long id) {
        postingService.deleteJobPosting(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
