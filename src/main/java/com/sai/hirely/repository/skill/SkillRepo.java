package com.sai.hirely.repository.skill;

import com.sai.hirely.models.utils.Skill;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepo extends JpaRepository<Skill,Long> {
    Optional<Skill> findByName(String name);
}
