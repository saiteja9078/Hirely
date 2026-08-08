package com.sai.hirely.repository.skill;

import com.sai.hirely.dto.SkillResponse;
import com.sai.hirely.models.utils.Skill;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepo extends JpaRepository<Skill,Long> {
    Optional<Skill> findByName(String name);
    List<SkillResponse> findAllProjectedBy();
}
