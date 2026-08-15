package com.sai.hirely.repository.candidate;

import java.util.List;
import java.util.Optional;

import com.sai.hirely.dto.skill.candidate.CandidateSkillDto;
import com.sai.hirely.models.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate,Long> {
    @Query("""
            select new com.sai.hirely.dto.skill.candidate.CandidateSkillDto(s.id, s.name, cs.proficiency)
            from Candidate c join c.candidateSkills cs join cs.skill s
            where c.id = :id
            order by s.name
            """)
    List<CandidateSkillDto> findAllCandidateSKills(Long id);

    Optional<Candidate> findByEmail(String email);
}
