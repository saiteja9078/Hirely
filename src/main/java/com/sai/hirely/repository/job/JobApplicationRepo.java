package com.sai.hirely.repository.job;

import com.sai.hirely.dto.candidate.CandidateCard;
import com.sai.hirely.models.job.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface JobApplicationRepo extends JpaRepository<JobApplication,Long> {

//    @Query("""
//    select new CandidateCard(
//        c.id,
//        c.firstName,
//        c.lastName,
//        c.profilePictureUrl.
//
//
//) from JobApplication app left join app.candidate c where app.id.jobId=:jobId
//""")
//    Page<CandidateCard> findAllCandidates(long jobId, Pageable pageable);
}
