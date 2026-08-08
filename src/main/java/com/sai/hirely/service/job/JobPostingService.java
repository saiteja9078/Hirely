package com.sai.hirely.service.job;

import com.sai.hirely.dto.job.JobCard;
import com.sai.hirely.dto.job.JobFilterRequest;
import com.sai.hirely.dto.job.JobPostingRequest;
import com.sai.hirely.dto.skill.job.JobCreateSkill;
import com.sai.hirely.dto.skill.job.JobExistingSkill;
import com.sai.hirely.models.job.JobPosting;
import com.sai.hirely.models.job.JobSkillRequirement;
import com.sai.hirely.models.utils.Skill;
import com.sai.hirely.repository.company.CompanyRepo;
import com.sai.hirely.repository.company.HiringManagerRepo;
import com.sai.hirely.repository.job.JobPostingRepo;
import com.sai.hirely.repository.role.RoleRepo;
import com.sai.hirely.repository.skill.JobSkillRequirementRepo;
import com.sai.hirely.repository.skill.SkillRepo;
import com.sai.hirely.repository.specifications.jobPosting.JobCriteriaApiRepo;
import com.sai.hirely.service.skill.SkillService;
import com.sai.hirely.exceptions.company.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobPostingService {
    private final JobSkillRequirementRepo jobSkillRequirementRepo;
    private final JobPostingRepo postingRepo;
    private final SkillService skillService;
    private final CompanyRepo companyRepo;
    private final HiringManagerRepo hrRepo;
    private final SkillRepo skillRepo;
    private final RoleRepo roleRepo;
    private final EntityManager entityManager;
    private final JobCriteriaApiRepo jobCaRepo;
    public JobPostingService(JobPostingRepo postingRepo,JobCriteriaApiRepo jobCaRepo, SkillService skillService, CompanyRepo companyRepo, HiringManagerRepo hrRepo, JobSkillRequirementRepo jobSkillRequirementRepo, SkillRepo skillRepo, RoleRepo roleRepo, EntityManager entityManager) {
        this.postingRepo = postingRepo;
        this.skillService = skillService;
        this.companyRepo = companyRepo;
        this.hrRepo = hrRepo;
        this.jobSkillRequirementRepo = jobSkillRequirementRepo;
        this.skillRepo = skillRepo;
        this.roleRepo = roleRepo;
        this.entityManager = entityManager;
        this.jobCaRepo = jobCaRepo;
    }
    @Transactional
    public JobPosting createJobPosting(JobPostingRequest request) {
        List<JobCreateSkill> createSkills = request.createSkills();
        List<JobExistingSkill> existingSkills = request.existingSkills();

        List<JobSkillRequirement> skillRequirements = new ArrayList<>();
        JobPosting posting = postingRepo.save(getJobPosting(request));
        if(createSkills!=null) {
            List<Skill> skills =
                    skillService.createSkills(createSkills);
            for (int i = 0; i < skills.size(); i++) {
                skillRequirements.add( new JobSkillRequirement(
                        posting,
                        skills.get(i),
                        createSkills.get(i).getProficiency(),
                        createSkills.get(i).isRequired()
                ));
            }
        }
        if(existingSkills!=null) {
            for (JobExistingSkill existingSKill : request.existingSkills()) {
                skillRequirements.add(new JobSkillRequirement(
                        posting,
                        skillRepo.getReferenceById(existingSKill.getId()),
                        existingSKill.getProficiency(),
                        existingSKill.isRequired()
                ));
            }
        }
        jobSkillRequirementRepo.saveAll(skillRequirements);
        entityManager.flush();
        entityManager.clear();

        return findFullJobDetails(posting.getId());
    }

    public JobPosting getJobPosting(JobPostingRequest request) {
        JobPosting posting = new JobPosting();
        posting.setCompany(companyRepo.getReferenceById(request.companyId()));
        posting.setHiringManager(hrRepo.getReferenceById(request.hiringManagerId()));
        posting.setDescription(request.description());
        posting.setMinimumExperienceInMonths(request.minimumExperienceInMonths());
        posting.setTitle(request.title());
        posting.setSalaryLower(request.salaryLower());
        posting.setSalaryHigher(request.salaryHigher());
        posting.setStatus(request.status());
        posting.setPostedAt(request.postedAt());
        posting.setExpiresAt(request.expiresAt());
        posting.setLocation(request.location());
        posting.setRole(roleRepo.getReferenceById(request.roleId()));
        posting.setWorkMode(request.workMode());
        return posting;
    }

    public JobPosting findFullJobDetails(Long jobId) {
        return postingRepo.findWholeObjectGraphById(jobId).orElseThrow(() -> new EntityNotFoundException("JobPosting", jobId));
    }

    public Page<JobCard> getPage(JobFilterRequest filterRequest, Pageable pageable) {
        return jobCaRepo.getJobCards(pageable,filterRequest);
    }

    @Transactional
    public void deleteJobPosting(Long id) throws EntityNotFoundException {
        if (!postingRepo.existsById(id)) {
            throw new EntityNotFoundException("JobPosting", id);
        }
        postingRepo.deleteById(id);
    }
}