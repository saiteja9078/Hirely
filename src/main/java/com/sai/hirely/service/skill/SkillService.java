package com.sai.hirely.service.skill;

import com.sai.hirely.dto.SkillResponse;
import com.sai.hirely.dto.skill.CreateSkill;
import com.sai.hirely.exceptions.company.EntityNotFoundException;
import com.sai.hirely.models.utils.Skill;
import com.sai.hirely.repository.skill.SkillRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {

    private SkillRepo skillRepo;

    public SkillService(SkillRepo repo, RepositoryMethodInvocationListener repositoryMethodInvocationListener) {
        this.skillRepo = repo;
    }

    @Transactional(readOnly = true)
    public Skill findByName(String name) throws EntityNotFoundException{
        return skillRepo.findByName(name).orElseThrow(() -> new EntityNotFoundException("Skill", name));
    }

    @Transactional
    public List<Skill> createSkills(List<? extends CreateSkill> createSkills) {
        List<Skill> sKills = new ArrayList<>();
        for(CreateSkill cSkill: createSkills) {
            try {
                sKills.add(skillRepo.save(new Skill(cSkill.getName())));
            } catch (DataIntegrityViolationException e) {
                sKills.add(skillRepo.findByName(cSkill.getName()).orElseThrow(() -> new EntityNotFoundException("Skill", cSkill.getName())));
            }
        }
        return sKills;
    }
    @Transactional(readOnly = true)
    public List<SkillResponse> findAll() {
        return skillRepo.findAllProjectedBy();
    }

    @Transactional(readOnly = true)
    public Skill findById(Long id) throws EntityNotFoundException {
        return skillRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Skill", id));
    }
}
