package com.sai.hirely.service.job;

import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.models.job.JobApplication;
import com.sai.hirely.repository.candidate.CandidateRepo;
import com.sai.hirely.repository.job.JobApplicationRepo;
import com.sai.hirely.repository.job.JobPostingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional // makes every public method transactional
public class JobApplicationService {
    private final JobApplicationRepo applicationRepo;
    private final CandidateRepo candidateRepo;
    private final JobPostingRepo jobPostingRepo;

    @Autowired
    public JobApplicationService(JobApplicationRepo applicationRepo, CandidateRepo candidateRepo, JobPostingRepo jobPostingRepo) {
        this.applicationRepo = applicationRepo;
        this.candidateRepo = candidateRepo;
        this.jobPostingRepo = jobPostingRepo;
    }
    @Transactional(readOnly = true)
    public void apply(Long jobId, Long candidateId) {
        JobApplication application = new JobApplication();
        application.setCandidate(candidateRepo.getReferenceById(candidateId));
        application.setJobPosting(jobPostingRepo.getReferenceById(jobId));
        applicationRepo.save(application);
    }

    public void deleteJobApplication(Long id) throws com.sai.hirely.exceptions.company.EntityNotFoundException {
        if (!applicationRepo.existsById(id)) {
            throw new com.sai.hirely.exceptions.company.EntityNotFoundException("JobApplication", id);
        }
        applicationRepo.deleteById(id);
    }

//    public Pageable findCandidates(long jobId,long limit,long offset) {
//        applicationRepo.findAllCandidates(jobId,limit,offset);
//    }
}
