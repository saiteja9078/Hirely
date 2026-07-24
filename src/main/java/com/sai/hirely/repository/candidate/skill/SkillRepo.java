package com.sai.hirely.repository.candidate.skill;

import com.sai.hirely.models.utils.Skill;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepo extends JpaRepository<Skill,Long> {
    Skill findByName(String name);
}
