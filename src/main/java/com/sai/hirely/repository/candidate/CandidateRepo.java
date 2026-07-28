package com.sai.hirely.repository.candidate;

import java.util.List;

import com.sai.hirely.dto.candidate.skill.CandidateSkillDto;
import com.sai.hirely.models.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate,Long> {
    @Query("select s.id,s.name,cs.proficiency from Candidate c left join c.candidateSkills cs left join cs.skill s where c.id =:id")
    List<CandidateSkillDto> findAllCandidateSKills(Long id);
}