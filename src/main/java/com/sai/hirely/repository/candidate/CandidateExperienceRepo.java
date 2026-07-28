package com.sai.hirely.repository.candidate;

import com.sai.hirely.dto.candidate.experience.CandidateExperienceResponse;
import com.sai.hirely.models.candidate.CandidateExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidateExperienceRepo extends JpaRepository<CandidateExperience, Long> {

    @Query("""
        select new com.sai.hirely.dto.candidate.experience.CandidateExperienceResponse(
            ex.id,
            ex.role.id,
                ex.role.name,
                    ex.organizationName,
                        ex.company.id,
                        ex.company.name,
                            ex.description,
                                ex.experienceInMonths)
             from CandidateExperience ex where ex.candidate.id =:candidateId
    """)
    List<CandidateExperienceResponse> findByCandidateId(Long candidateId);
}
