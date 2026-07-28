package com.sai.hirely.service.candidate;
import com.sai.hirely.dto.candidate.skill.CandidateSkillsProjection;
import com.sai.hirely.dto.candidate.skill.CandidateSkillsRequest;
import com.sai.hirely.dto.candidate.skill.CreateSkill;
import com.sai.hirely.dto.candidate.skill.ExistingSkill;
import com.sai.hirely.exceptions.candidate.CandidateNotFoundException;
import com.sai.hirely.exceptions.candidate.CandidateSkillNotFoundException;
import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.models.candidate.CandidateSkill;
import com.sai.hirely.models.candidate.CandidateSkillKey;
import com.sai.hirely.models.enums.Proficiency;
import com.sai.hirely.models.utils.Skill;
import com.sai.hirely.repository.candidate.CandidateRepo;
import com.sai.hirely.repository.candidate.CandidateSkillRepo;
import com.sai.hirely.repository.skill.SkillRepo;
import com.sai.hirely.service.skill.SkillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import java.util.*;

@Service
public class CandidateSkillService {

    private final RequestToViewNameTranslator requestToViewNameTranslator;
    private CandidateSkillRepo candidateSkillsRepo;
    private SkillRepo skillsRepo;
    private SkillService skillService;
    private CandidateRepo candidateRepo;

    public CandidateSkillService(CandidateSkillRepo skillRepo, CandidateRepo candidateRepo, SkillService skillService, SkillRepo skillsRepo, RequestToViewNameTranslator requestToViewNameTranslator) {
        this.skillService = skillService;
        this.candidateSkillsRepo = skillRepo;
        this.candidateRepo = candidateRepo;
        this.skillsRepo = skillsRepo;
        this.requestToViewNameTranslator = requestToViewNameTranslator;
    }
    @Transactional(readOnly = true)
    public List<CandidateSkillsProjection> findAllByCandidateId(Long candidateId) {
        List<CandidateSkillsProjection> projections = candidateRepo.findAllCandidateSKills(candidateId);
        if(projections.isEmpty()) {
            throw new CandidateNotFoundException(candidateId);
        }
        return projections;
    }
    @Transactional
    public void addSkills( CandidateSkillsRequest skillsRequest) {
        Candidate candidate = candidateRepo.getReferenceById(skillsRequest.candidateId());
        List<Skill> skillReferences =  skillsRequest.addExistingSkills()
                            .stream().map((a) -> skillsRepo.getReferenceById(a.id())).toList();
        List<Skill> createdSkills = skillService.createSkills(skillsRequest.createNewSkills());
        List<CandidateSkill> candidateSkills = new ArrayList<>();
        List<ExistingSkill> existing = skillsRequest.addExistingSkills();
        List<CreateSkill> created = skillsRequest.createNewSkills();
        for(int i=0;i<skillReferences.size();i++) {
            candidateSkills.add(new CandidateSkill(
                    candidate, skillReferences.get(i),existing.get(i).proficiency()
            ));
        }
        for(int i=0;i<createdSkills.size();i++) {
            candidateSkills.add(
                    new CandidateSkill(candidate,createdSkills.get(i),created.get(i).proficiency())
            );
        }
        candidateSkillsRepo.saveAll(candidateSkills);
     }

    public void updateSkill(CandidateSkillKey candidateSkillId, Proficiency proficiency) {
        candidateSkillsRepo.findById(candidateSkillId)
                .orElseThrow(() -> new CandidateSkillNotFoundException(candidateSkillId))
                .setProficiency(proficiency);
    }
}
