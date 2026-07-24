package com.sai.hirely.service.candidate.skill;

import com.sai.hirely.models.utils.Skill;
import com.sai.hirely.repository.candidate.skill.SkillRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SkillService {

    private SkillRepo skillRepo;
    public SkillService(SkillRepo repo) {
        this.skillRepo = repo;
    }
    @Transactional(readOnly = true)
    public Skill findByName(String name) {
        return skillRepo.findByName(name);
    }
    @Transactional
    public Skill createSkill(String name) {
        Skill skill = new Skill(name);
        return skillRepo.saveAndFlush(skill); // To save concurrent skill creations
    }
}
