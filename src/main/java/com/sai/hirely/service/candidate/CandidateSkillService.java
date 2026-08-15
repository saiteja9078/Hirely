package com.sai.hirely.service.candidate;
import com.sai.hirely.dto.skill.candidate.CandidateSkillDto;
import com.sai.hirely.dto.skill.candidate.CandidateSkillsRequest;
import com.sai.hirely.dto.skill.CreateSkill;
import com.sai.hirely.dto.skill.ExistingSkill;
import com.sai.hirely.exceptions.company.EntityNotFoundException;
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

import java.util.*;

@Service
public class CandidateSkillService {

    private CandidateSkillRepo candidateSkillsRepo;
    private SkillRepo skillsRepo;
    private SkillService skillService;
    private CandidateRepo candidateRepo;

    public CandidateSkillService(CandidateSkillRepo skillRepo, CandidateRepo candidateRepo, SkillService skillService, SkillRepo skillsRepo) {
        this.skillService = skillService;
        this.candidateSkillsRepo = skillRepo;
        this.candidateRepo = candidateRepo;
        this.skillsRepo = skillsRepo;
    }
    @Transactional(readOnly = true)
    public List<CandidateSkillDto> findAllByCandidateId(Long candidateId) {
        return candidateRepo.findAllCandidateSKills(candidateId);
    }
    @Transactional
    public void addSkills( CandidateSkillsRequest skillsRequest) {
        Candidate candidate = candidateRepo.getReferenceById(skillsRequest.candidateId());
        List<ExistingSkill> existing = Optional.ofNullable(skillsRequest.addExistingSkills()).orElseGet(List::of);
        List<CreateSkill> created = Optional.ofNullable(skillsRequest.createNewSkills()).orElseGet(List::of);
        List<Skill> skillReferences = existing
                            .stream().map((a) -> skillsRepo.getReferenceById(a.getId())).toList();
        List<Skill> createdSkills = created.isEmpty() ? List.of() : skillService.createSkills(created);
        List<CandidateSkill> candidateSkills = new ArrayList<>();
        for(int i=0;i<skillReferences.size();i++) {
            CandidateSkillKey key = new CandidateSkillKey(skillReferences.get(i).getId(), candidate.getId());
            if (!candidateSkillsRepo.existsById(key)) {
                candidateSkills.add(new CandidateSkill(candidate, skillReferences.get(i), existing.get(i).getProficiency()));
            }
        }
        for(int i=0;i<createdSkills.size();i++) {
            CandidateSkillKey key = new CandidateSkillKey(createdSkills.get(i).getId(), candidate.getId());
            if (!candidateSkillsRepo.existsById(key)) {
                candidateSkills.add(new CandidateSkill(candidate, createdSkills.get(i), created.get(i).getProficiency()));
            }
        }
        if (!candidateSkills.isEmpty()) {
            candidateSkillsRepo.saveAll(candidateSkills);
        }
     }

    @Transactional
    public void addExistingSkillIds(Long candidateId, List<Long> skillIds) {
        if (skillIds == null || skillIds.isEmpty()) {
            return;
        }
        List<ExistingSkill> existingSkills = skillIds.stream()
                .distinct()
                .map(skillId -> new ExistingSkill(skillId, Proficiency.BEGINNER))
                .toList();
        addSkills(new CandidateSkillsRequest(candidateId, existingSkills, List.of()));
    }

    public void updateSkill(CandidateSkillKey candidateSkillId, Proficiency proficiency) {
        CandidateSkill skill = candidateSkillsRepo.findById(candidateSkillId).orElseThrow(
                () -> new EntityNotFoundException("CandidateSkill", candidateSkillId)
        );
        skill.setProficiency(proficiency);
    }

    @Transactional
    public void deleteSkill(CandidateSkillKey candidateSkillId) {
        if (!candidateSkillsRepo.existsById(candidateSkillId)) {
            throw new EntityNotFoundException("CandidateSkill", candidateSkillId);
        }
        candidateSkillsRepo.deleteById(candidateSkillId);
    }
}
