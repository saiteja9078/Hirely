package com.sai.hirely.repository.job;

import com.sai.hirely.models.job.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepo extends JpaRepository<JobApplication,Long> {
    @Query("""
            select app from JobApplication app
            join fetch app.jobPosting job
            join fetch job.company
            where app.candidate.id = :candidateId
            order by app.appliedAt desc
            """)
    List<JobApplication> findForCandidate(Long candidateId);

    int countByJobPostingId(Long jobId);

    @Query("""
            select distinct app from JobApplication app
            join fetch app.candidate candidate
            left join fetch candidate.candidateSkills candidateSkill
            left join fetch candidateSkill.skill
            where app.jobPosting.id = :jobId
            order by app.appliedAt desc
            """)
    List<JobApplication> findForJobWithCandidate(Long jobId);

    @Query("""
            select app from JobApplication app
            join fetch app.jobPosting job
            join fetch job.company
            where app.id = :applicationId
            """)
    java.util.Optional<JobApplication> findWithJobById(Long applicationId);
}
