package com.sai.hirely.repository.candidate.skill;

import com.sai.hirely.models.candidate.CandidateSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface CandidateSkillRepo extends JpaRepository<CandidateSkill,Long> {

    @Query("select cs,cs.skill from CandidateSkill cs join fetch cs.skill")
    List<CandidateSkill> findAllByCandidateSkills(Long id);

}
