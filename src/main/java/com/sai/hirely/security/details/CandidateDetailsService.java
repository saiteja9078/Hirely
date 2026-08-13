package com.sai.hirely.security.details;

import com.sai.hirely.exceptions.EmailNotFounctException;
import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.repository.candidate.CandidateRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("candidateDetailsService")
public class CandidateDetailsService implements UserDetailsService {
    private final CandidateRepo repo;
    public CandidateDetailsService(CandidateRepo repo) {
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Candidate candidate = repo.findByEmail(username).orElseThrow(() -> new EmailNotFounctException("Email: "+username+" not found"));
        return new CustomUserDetails(
                candidate.getId(),
                candidate.getEmail(),
                candidate.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_CANDIDATE")),
                AccountType.CANDIDATE
        );
    }
}
