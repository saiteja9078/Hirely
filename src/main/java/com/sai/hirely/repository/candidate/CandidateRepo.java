package com.sai.hirely.repository.candidate;

import com.sai.hirely.models.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate,Long> {

}