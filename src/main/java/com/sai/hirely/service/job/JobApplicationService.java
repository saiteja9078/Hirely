package com.sai.hirely.service.job;

import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.models.job.JobApplication;
import com.sai.hirely.repository.candidate.CandidateRepo;
import com.sai.hirely.repository.job.JobApplicationRepo;
import com.sai.hirely.repository.job.JobPostingRepo;
import com.sai.hirely.service.email.EmailService;
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
    private final EmailService emailService;

    @Autowired
    public JobApplicationService(JobApplicationRepo applicationRepo, CandidateRepo candidateRepo, JobPostingRepo jobPostingRepo, EmailService emailService) {
        this.applicationRepo = applicationRepo;
        this.candidateRepo = candidateRepo;
        this.jobPostingRepo = jobPostingRepo;
        this.emailService = emailService;
    }
    @Transactional
    public void apply(Long jobId, Long candidateId, String coverLetter) {
        JobApplication application = new JobApplication();
        Candidate candidate = candidateRepo.getReferenceById(candidateId);
        com.sai.hirely.models.job.JobPosting jobPosting = jobPostingRepo.getReferenceById(jobId);
        
        application.setCandidate(candidate);
        application.setJobPosting(jobPosting);
        application.setCoverLetter(coverLetter);
        applicationRepo.save(application);

        emailService.sendJobApplicationEmail(
                candidate.getEmail(), 
                candidate.getFirstName(), 
                jobPosting.getTitle(), 
                jobPosting.getCompany().getName());
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
