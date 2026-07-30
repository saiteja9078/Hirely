package com.sai.hirely.repository.job.posting;

import com.sai.hirely.models.job.JobPosting;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JobPostingRepo extends JpaRepository<JobPosting,Long>,
                                        JpaSpecificationExecutor<JobPosting>
{


    // distinct
    @Query(""" 
    select distinct ps from JobPosting ps
    join fetch ps.company join fetch ps.skillRequirements sr join fetch sr.skill
    join fetch ps.role r where
    ps.id =:id
""")
    Optional<JobPosting> findWholeObjectGraphById(Long id);
}
