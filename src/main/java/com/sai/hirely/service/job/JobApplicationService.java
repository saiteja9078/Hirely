package com.sai.hirely.service.job;

import com.sai.hirely.dto.NotificationResponse;
import com.sai.hirely.dto.job.ApplicantResponse;
import com.sai.hirely.dto.job.ApplicationResponse;
import com.sai.hirely.dto.candidate.experience.CandidateExperienceResponse;
import com.sai.hirely.dto.skill.candidate.CandidateSkillDto;
import com.sai.hirely.exceptions.company.EntityNotFoundException;
import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.models.candidate.CandidateInterestsKey;
import com.sai.hirely.models.enums.ApplicationStatus;
import com.sai.hirely.models.job.JobApplication;
import com.sai.hirely.models.job.JobPosting;
import com.sai.hirely.repository.candidate.CandidateRepo;
import com.sai.hirely.repository.job.JobApplicationRepo;
import com.sai.hirely.repository.job.JobPostingRepo;
import com.sai.hirely.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.sai.hirely.service.storage.ResumeService;

@Service
@Transactional // makes every public method transactional
public class JobApplicationService {
    private static final Logger log = LoggerFactory.getLogger(JobApplicationService.class);
    private final JobApplicationRepo applicationRepo;
    private final CandidateRepo candidateRepo;
    private final JobPostingRepo jobPostingRepo;
    private final EmailService emailService;

    private final com.sai.hirely.repository.candidate.CandidateInterestsRepo candidateInterestsRepo;
    private final ResumeService resumeService;

    @Autowired
    public JobApplicationService(JobApplicationRepo applicationRepo, CandidateRepo candidateRepo, JobPostingRepo jobPostingRepo, EmailService emailService, com.sai.hirely.repository.candidate.CandidateInterestsRepo candidateInterestsRepo, ResumeService resumeService) {
        this.applicationRepo = applicationRepo;
        this.candidateRepo = candidateRepo;
        this.jobPostingRepo = jobPostingRepo;
        this.emailService = emailService;
        this.candidateInterestsRepo = candidateInterestsRepo;
        this.resumeService = resumeService;
    }
    @Transactional
    public void apply(Long jobId, Long candidateId, String coverLetter, Long resumeId, boolean alerts) {
        JobApplication application = new JobApplication();
        Candidate candidate = candidateRepo.findById(candidateId)
                .orElseThrow(() -> new EntityNotFoundException("Candidate", candidateId));
        com.sai.hirely.models.job.JobPosting jobPosting = jobPostingRepo.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("JobPosting", jobId));
        
        application.setCandidate(candidate);
        application.setJobPosting(jobPosting);
        application.setCoverLetter(coverLetter);
        if (resumeId != null) {
            com.sai.hirely.models.utils.Resume resume = candidate.getResumes().stream()
                    .filter(r -> r.getId().equals(resumeId))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Resume", resumeId));
            application.setResume(resume);
        }
        application.setStatus(ApplicationStatus.APPLIED);
        application.setAppliedAt(LocalDateTime.now());
        applicationRepo.save(application);

        if (alerts && jobPosting.getRole() != null) {
            com.sai.hirely.models.candidate.CandidateInterests interest = new com.sai.hirely.models.candidate.CandidateInterests();
            interest.setId(new CandidateInterestsKey(candidateId, jobPosting.getRole().getId()));
            candidateInterestsRepo.save(interest);
        }

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

    public void deleteJobApplication(Long id, Long candidateId) {
        JobApplication application = applicationRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("JobApplication", id));
        if (!application.getCandidate().getId().equals(candidateId)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.FORBIDDEN, "You can only withdraw your own application.");
        }
        if (application.getStatus() != com.sai.hirely.models.enums.ApplicationStatus.REJECTED) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST, "You can only delete applications that have been rejected.");
        }
        applicationRepo.delete(application);
    }

    @Transactional(readOnly = true)
    public List<ApplicationResponse> findByCandidateId(Long candidateId) {
        return applicationRepo.findForCandidate(candidateId).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public com.sai.hirely.dto.job.DetailedApplicationResponse getApplicationDetails(Long id, Long candidateId) {
        JobApplication application = applicationRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("JobApplication", id));
        if (!application.getCandidate().getId().equals(candidateId)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.FORBIDDEN, "You can only view your own application.");
        }
        JobPosting job = application.getJobPosting();
        int totalApplicants = applicationRepo.countByJobPostingId(job.getId());
        
        return new com.sai.hirely.dto.job.DetailedApplicationResponse(
                application.getId(),
                application.getStatus(),
                application.getAppliedAt(),
                application.getCoverLetter(),
                application.getResume() != null ? application.getResume().getId() : null,
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getLocation(),
                job.getWorkMode() != null ? job.getWorkMode().name() : null,
                job.getSalaryLower(),
                job.getSalaryHigher(),
                job.getCompany().getId(),
                job.getCompany().getName(),
                totalApplicants
        );
    }

    @Transactional(readOnly = true)
    public List<ApplicantResponse> findApplicants(Long jobId) {
        return applicationRepo.findForJobWithCandidate(jobId).stream().map(application -> {
            Candidate candidate = application.getCandidate();
            List<CandidateSkillDto> skills = candidate.getCandidateSkills().stream()
                    .map(skill -> new CandidateSkillDto(skill.getSkill().getId(), skill.getSkill().getName(), skill.getProficiency()))
                    .toList();
            List<CandidateExperienceResponse> experiences = candidate.getCandidateExperiences().stream()
                    .map(exp -> new CandidateExperienceResponse(
                            exp.getId(),
                            exp.getRole() != null ? exp.getRole().getId() : null,
                            exp.getRole() != null ? exp.getRole().getName() : null,
                            exp.getOrganizationName(),
                            exp.getCompany() != null ? exp.getCompany().getId() : null,
                            exp.getCompany() != null ? exp.getCompany().getName() : null,
                            exp.getDescription(),
                            exp.getFromDate(),
                            exp.getToDate()
                    ))
                    .toList();
            return new ApplicantResponse(
                    application.getId(), candidate.getId(), candidate.getFirstName(), candidate.getLastName(),
                    candidate.getEmail(), candidate.getDescription(), candidate.getLocation(), skills, experiences,
                    application.getStatus(), application.getAppliedAt(), application.getCoverLetter(),
                    application.getResume() != null ? application.getResume().getId() : null,
                    null); // Explicitly don't fetch actualName to prevent downloading the full Resume data
        }).toList();
    }

    @Transactional
    public ApplicationResponse updateStatus(Long applicationId, ApplicationStatus status, CustomUserDetails user) {
        JobApplication application = applicationRepo.findWithJobById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("JobApplication", applicationId));
        assertCanManage(application, user);
        application.setStatus(status);
        return toResponse(application);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Resource> downloadResume(Long applicationId, CustomUserDetails user) {
        JobApplication application = applicationRepo.findWithJobById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("JobApplication", applicationId));
        assertCanManage(application, user);
        
        if (application.getResume() == null) {
            return ResponseEntity.notFound().build();
        }

        Long resumeId = application.getResume().getId();
        Long candidateId = application.getCandidate().getId();
        
        try {
            Resource resource = resumeService.download(resumeId, candidateId);
            com.sai.hirely.models.utils.Resume resumeEntity = resumeService.getResumeEntity(resumeId, candidateId);
            
            String contentType = "application/octet-stream";
            try {
                String probedContentType = Files.probeContentType(Path.of(resumeEntity.getStoredPath()));
                if (probedContentType != null) {
                    contentType = probedContentType;
                }
            } catch (IOException ex) {
                // ignore
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resumeEntity.getActualName() + "\"")
                    .body(resource);
        } catch (Exception e) {
            log.error("Error downloading resume: {}", e.getMessage(), e);
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional(readOnly = true)
    public void assertCanManageJob(Long jobId, CustomUserDetails user) {
        JobApplication probe = new JobApplication();
        probe.setJobPosting(jobPostingRepo.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("JobPosting", jobId)));
        assertCanManage(probe, user);
    }

    @Transactional(readOnly = true)
    public List<NotificationResponse> notificationsForCandidate(Long candidateId) {
        return applicationRepo.findForCandidate(candidateId).stream().map(application -> new NotificationResponse(
                "application-" + application.getId(),
                notificationTitle(application.getStatus()),
                application.getJobPosting().getCompany().getName() + " · " + application.getJobPosting().getTitle(),
                application.getAppliedAt()
        )).toList();
    }

    private ApplicationResponse toResponse(JobApplication application) {
        var job = application.getJobPosting();
        return new ApplicationResponse(application.getId(), job.getId(), job.getTitle(), job.getCompany().getId(),
                job.getCompany().getName(), application.getStatus(), application.getAppliedAt(), application.getCoverLetter());
    }

    private void assertCanManage(JobApplication application, CustomUserDetails user) {
        var job = application.getJobPosting();
        boolean isCompany = user.getType() == AccountType.COMPANY && job.getCompany().getId().equals(user.getId());
        boolean isHiringManager = user.getType() == AccountType.HIRING_MANAGER
                && job.getHiringManager() != null && job.getHiringManager().getId().equals(user.getId());
        if (!isCompany && !isHiringManager) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.FORBIDDEN, "You cannot manage applications for this job.");
        }
    }

    private String notificationTitle(ApplicationStatus status) {
        return switch (status) {
            case APPLIED -> "Application submitted";
            case SCREENING -> "Your application is under review";
            case INTERVIEW -> "Interview stage update";
            case OFFER -> "You received an offer";
            case REJECTED -> "Application update";
            case APPROVED -> "Your application was approved";
        };
    }

//    public Pageable findCandidates(long jobId,long limit,long offset) {
//        applicationRepo.findAllCandidates(jobId,limit,offset);
//    }
}
