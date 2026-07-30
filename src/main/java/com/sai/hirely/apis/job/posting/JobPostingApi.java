package com.sai.hirely.apis.job.posting;

import com.sai.hirely.dto.job.JobCard;
import com.sai.hirely.dto.job.JobFilterRequest;
import com.sai.hirely.dto.job.JobPostingRequest;
import com.sai.hirely.dto.job.JobPostingResponse;
import com.sai.hirely.mappers.JobPostingMapper;
import com.sai.hirely.service.job.posting.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/jobs")
public class JobPostingApi {
    private final JobPostingMapper jobPostingMapper;
    private final JobPostingService postingService;
    @Autowired
    public JobPostingApi(JobPostingService postingService, JobPostingMapper jobPostingMapper) {
        this.postingService = postingService;
        this.jobPostingMapper = jobPostingMapper;
    }
    @PostMapping
    // redirect to the application page and show them
    public ResponseEntity<JobPostingResponse> createJobPosting(
            @RequestBody JobPostingRequest request
            ) {
        ResponseEntity<JobPostingResponse> jobPosting =  ResponseEntity.status(HttpStatus.OK)
                .body(jobPostingMapper.toResponse(postingService.createJobPosting(request)));
        // emailService.sendEmail(posting)
        return jobPosting;
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
}
