package com.sai.hirely.service.candidate;

import com.sai.hirely.exceptions.candidate.CandidateNotFoundException;
import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.repository.candidate.CandidateRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CandidateService {

    private CandidateRepo candidateRepo;
    @Autowired
    public CandidateService(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }
    @Transactional(readOnly = true)
    public Candidate findById(Long id) throws CandidateNotFoundException{
        return candidateRepo.findById(id).orElseThrow(
                () -> new CandidateNotFoundException(id)
        );
    }
    @Transactional
    public Candidate addCandidate(Candidate entity) {
        return candidateRepo.save(entity);
    }
}