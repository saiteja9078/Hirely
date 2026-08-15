package com.sai.hirely.repository.candidate;

import com.sai.hirely.models.utils.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepo extends JpaRepository<Resume,Long> {
    List<Resume> findByCandidateIdOrderByUploadedAtDesc(Long candidateId);
    Optional<Resume> findByIdAndCandidateId(Long id, Long candidateId);
}
