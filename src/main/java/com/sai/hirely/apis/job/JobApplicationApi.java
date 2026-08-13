package com.sai.hirely.apis.job;

import com.sai.hirely.service.job.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
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
            @RequestParam Long jobId,
            @RequestParam Long candidateId,
            @RequestParam(required = false) String coverLetter
    ) {
        applicationService.apply(jobId, candidateId, coverLetter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id) {
        applicationService.deleteJobApplication(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    @GetMapping("/findAll/{jobId}")
//    public Page findAllCandidates(
//            @PathVariable Long jobId
//    ) {
////        return applicationService.findCandidates(jobId);
//        return null;
//    }
}
