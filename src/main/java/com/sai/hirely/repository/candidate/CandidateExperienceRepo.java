package com.sai.hirely.repository.candidate;

import com.sai.hirely.models.candidate.CandidateExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CandidateExperienceRepo extends JpaRepository<CandidateExperience, Long> {

    List<CandidateExperience> findByCandidateIdOrderByFromDateDesc(Long candidateId);

    Optional<CandidateExperience> findByIdAndCandidateId(Long id, Long candidateId);

    boolean existsByCandidateIdAndCompanyId(Long candidateId, Long companyId);
}
