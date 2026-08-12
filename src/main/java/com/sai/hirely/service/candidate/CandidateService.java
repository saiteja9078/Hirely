package com.sai.hirely.service.candidate;

import com.sai.hirely.dto.candidate.CandidateRequest;
import com.sai.hirely.exceptions.company.EntityNotFoundException;
import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.repository.candidate.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CandidateService {

    private final CandidateRepo candidateRepo;
    private final PasswordEncoder encoder;
    @Autowired
    public CandidateService(CandidateRepo candidateRepo,PasswordEncoder encoder) {
        this.candidateRepo = candidateRepo;
        this.encoder = encoder;
    }
    @Transactional(readOnly = true)
    public Candidate findById(Long id) throws EntityNotFoundException{
        return candidateRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Candidate", id)
        );
    }
    @Transactional
    public Candidate addCandidate(Candidate entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        return candidateRepo.save(entity);
    }
    @Transactional
    public Candidate updateCandidate(Long id, CandidateRequest request) throws EntityNotFoundException{
        Candidate candidate = findById(id);
        candidate.setFirstName(request.firstName());
        candidate.setLastName(request.lastName());
        candidate.setAge(request.age());
        candidate.setGender(request.gender());
        candidate.setEmail(request.email());
        candidate.setDescription(request.description());
        candidate.setLocation(request.location());
        return candidate;
    }

    @Transactional
    public void deleteCandidate(Long id) throws EntityNotFoundException {
        if (!candidateRepo.existsById(id)) {
            throw new EntityNotFoundException("Candidate", id);
        }
        candidateRepo.deleteById(id);
    }
}