package com.sai.hirely.service.candidate;

import com.sai.hirely.dto.candidate.CandidateRequest;
import com.sai.hirely.exceptions.company.EntityNotFoundException;
import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.repository.candidate.CandidateRepo;
import com.sai.hirely.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CandidateService {

    private final CandidateRepo candidateRepo;
    private final PasswordEncoder encoder;
    private final EmailService emailService;

    @Autowired
    public CandidateService(CandidateRepo candidateRepo, PasswordEncoder encoder, EmailService emailService) {
        this.candidateRepo = candidateRepo;
        this.encoder = encoder;
        this.emailService = emailService;
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
        Candidate savedCandidate = candidateRepo.save(entity);
        emailService.sendWelcomeEmail(savedCandidate.getEmail(), savedCandidate.getFirstName(), "Candidate");
        return savedCandidate;
    }
    @Transactional
    public Candidate updateCandidate(Long id, CandidateRequest request) throws EntityNotFoundException{
        Candidate candidate = findById(id);
        if (request.firstName() != null) candidate.setFirstName(request.firstName());
        if (request.lastName() != null) candidate.setLastName(request.lastName());
        if (request.age() != null) candidate.setAge(request.age());
        if (request.gender() != null) candidate.setGender(request.gender());
        if (request.email() != null) candidate.setEmail(request.email());
        if (request.description() != null) candidate.setDescription(request.description());
        if (request.location() != null) candidate.setLocation(request.location());
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
