package com.sai.hirely.service.candidate.skill;
import com.sai.hirely.dto.candidate.skill.CandidateSkillsRequest;
import com.sai.hirely.dto.candidate.skill.SkillDto;
import com.sai.hirely.exceptions.candidate.CandidateNotFoundException;
import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.models.candidate.CandidateSkill;
import com.sai.hirely.models.utils.Skill;
import com.sai.hirely.repository.candidate.skill.CandidateSkillRepo;
import com.sai.hirely.service.candidate.CandidateService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateSkillService {

    private CandidateSkillRepo skillsRepo;
    private CandidateService candidateService;
    private SkillService skillService;

    public CandidateSkillService(CandidateSkillRepo skillRepo,CandidateService candidateService,SkillService skillService) {
        this.skillService = skillService;
        this.skillsRepo = skillRepo;
        this.candidateService = candidateService;
    }
    @Transactional(readOnly = true)
    public List<CandidateSkill> getSkills(Long candidateId) throws CandidateNotFoundException {
        candidateService.findById(candidateId); //validate candidate id
        return skillsRepo.findAllByCandidateSkills(candidateId);
    }
    @Transactional
    public List<CandidateSkill> addSkills(CandidateSkillsRequest candidateSkills) throws CandidateNotFoundException {
        Candidate candidate = candidateService.findById(candidateSkills.candidateId());
        List<CandidateSkill> skillsList = new ArrayList<>();
        for(SkillDto dto : candidateSkills.skillRequestList()) {
            Skill skill = findOrCreate(dto.name());
            CandidateSkill candidateSkill = new CandidateSkill(candidate,skill,dto.proficiency());
            skillsList.add(candidateSkill);
        }
        return skillsRepo.saveAll(skillsList);
    }
    public Skill findOrCreate(String name) {
        Skill addedSkill = skillService.findByName(name);
        if(addedSkill!=null) return addedSkill;
        try {
            return skillService.createSkill(name);
        } catch (DataIntegrityViolationException e) {
            return skillService.findByName(name);
        }
    }
}
