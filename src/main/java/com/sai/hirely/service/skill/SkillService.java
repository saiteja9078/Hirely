package com.sai.hirely.service.skill;

import com.sai.hirely.dto.candidate.skill.CreateSkill;
import com.sai.hirely.exceptions.candidate.SkillNotFoundException;
import com.sai.hirely.models.utils.Skill;
import com.sai.hirely.repository.skill.SkillRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {

    private SkillRepo skillRepo;

    public SkillService(SkillRepo repo) {
        this.skillRepo = repo;
    }

    @Transactional(readOnly = true)
    public Skill findByName(String name) throws SkillNotFoundException{
        return skillRepo.findByName(name).orElseThrow(() -> new SkillNotFoundException(name));
    }

    @Transactional
    public List<Skill> createSkills(List<CreateSkill> createSkills) {
        List<Skill> sKills = new ArrayList<>();
        for(CreateSkill cSkill: createSkills) {
            try {
                sKills.add(skillRepo.save(new Skill(cSkill.name())));
            } catch (DataIntegrityViolationException e) {
                sKills.add(skillRepo.findByName(cSkill.name()).orElseThrow(() -> new SkillNotFoundException(cSkill.name())));
            }
        }
        return sKills;
    }

    @Transactional(readOnly = true)
    public Skill findById(Long id) throws SkillNotFoundException {
        return skillRepo.findById(id).orElseThrow(() -> new SkillNotFoundException(id));
    }
}
