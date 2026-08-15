package com.sai.hirely.repository.job;

import com.sai.hirely.models.job.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface JobPostingRepo extends JpaRepository<JobPosting,Long>,
                                        JpaSpecificationExecutor<JobPosting>
{


    // distinct
    @Query(""" 
    select distinct ps from JobPosting ps
    join fetch ps.company left join fetch ps.skillRequirements sr left join fetch sr.skill
    left join fetch ps.role r where
    ps.id =:id
""")
    Optional<JobPosting> findWholeObjectGraphById(Long id);

    @Query("""
            select distinct ps from JobPosting ps
            join fetch ps.company
            left join fetch ps.skillRequirements sr
            left join fetch sr.skill
            left join fetch ps.role
            where ps.company.id = :companyId
            order by ps.postedAt desc
            """)
    List<JobPosting> findWholeObjectGraphByCompanyId(Long companyId);

    @Query("""
            select distinct ps from JobPosting ps
            join fetch ps.company
            left join fetch ps.skillRequirements sr
            left join fetch sr.skill
            left join fetch ps.role
            where ps.hiringManager.id = :hiringManagerId
            order by ps.postedAt desc
            """)
    List<JobPosting> findWholeObjectGraphByHiringManagerId(Long hiringManagerId);

    Page<JobPosting> findByCompanyId(@Param("companyId") Long companyId, Pageable pageable);
}
