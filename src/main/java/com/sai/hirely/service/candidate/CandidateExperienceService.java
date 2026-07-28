package com.sai.hirely.service.candidate;
import com.sai.hirely.dto.candidate.experience.CandidateExperienceRequest;
import com.sai.hirely.dto.candidate.experience.ExperienceDto;
import com.sai.hirely.exceptions.candidate.CandidateExperienceNotFoundException;
import com.sai.hirely.models.candidate.CandidateExperience;
import com.sai.hirely.repository.candidate.CandidateExperienceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class CandidateExperienceService {

    private CandidateService candidateService;
    private CandidateExperienceRepo experienceRepo;

    @Autowired
    public CandidateExperienceService(CandidateService candidateService,CandidateExperienceRepo candidateExperienceRepo){
        this.experienceRepo = candidateExperienceRepo;
        this.candidateService = candidateService;
    }
    @Transactional(readOnly = true)
    public CandidateExperience findCandidateExperienceById(Long experienceId,Long candidateId) {
        candidateService.findById(candidateId);
        return experienceRepo.findById(experienceId).orElseThrow(() -> new CandidateExperienceNotFoundException(experienceId));
    }
    @Transactional(readOnly = true)
    public List<CandidateExperience> getCandidateExperiences(Long candidateId) {
        candidateService.findById(candidateId);
        return experienceRepo.findByCandidateId(candidateId);
    }
    @Transactional
    public void addCandidateExperiences(CandidateExperienceRequest request) {
        candidateService.findById(request.candidateId());
        List<Long> roleIds = request.experienceList().stream().map(ExperienceDto::roleId).toList();
    }
}
