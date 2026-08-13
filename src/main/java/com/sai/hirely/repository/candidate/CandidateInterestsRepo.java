package com.sai.hirely.repository.candidate;

import com.sai.hirely.service.candidate.CandidateInterests;
import com.sai.hirely.service.candidate.CandidateInterestsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateInterestsRepo extends JpaRepository<CandidateInterests, CandidateInterestsKey> {
    List<CandidateInterests> findByIdRoleId(Long roleId);
}
