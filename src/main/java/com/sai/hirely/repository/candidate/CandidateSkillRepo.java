package com.sai.hirely.repository.candidate;

import com.sai.hirely.models.candidate.CandidateSkill;
import com.sai.hirely.models.candidate.CandidateSkillKey;
import com.sai.hirely.models.utils.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface CandidateSkillRepo extends JpaRepository<CandidateSkill, CandidateSkillKey> {
    @Query("select cs from CandidateSkill cs join fetch cs.skill")
    List<CandidateSkill> findAllByCandidateSkills(Long id);
}
