package com.sai.hirely.repository.specifications.jobPosting;

import com.sai.hirely.dto.job.JobCard;
import com.sai.hirely.dto.job.JobFilterRequest;
import com.sai.hirely.dto.job.SortOrder;
import com.sai.hirely.models.company.Company;
import com.sai.hirely.models.job.JobPosting;
import com.sai.hirely.models.job.JobSkillRequirement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component
public class JobCriteriaApiRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public PageImpl<JobCard> getJobCards(Pageable pageable, JobFilterRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<JobCard> query = cb.createQuery(JobCard.class);
        Root<JobPosting> root = query.from(JobPosting.class);
        Join<JobPosting, Company> companyJoin = root.join("company",JoinType.INNER);
        query.distinct(true);
        //Build predicates
        List<Predicate> predicates = buildPredicates(request,root,cb);
        Predicate pred = cb.and(predicates.toArray(new Predicate[0]));
        query.where(pred);
        query.select(
                cb.construct(JobCard.class,
                    root.get("id"),
                    root.get("title"),
                    root.get("salaryLower"),
                    root.get("salaryHigher"),
                    root.get("postedAt"),
                    root.get("workMode"),
                    root.get("minimumExperienceInMonths"),
                    companyJoin.get("companyProfileUrl"),
                    companyJoin.get("name")
                        )
                );
        if (request.sortField() != null) {
            query.orderBy(
                    request.sortOrder() == SortOrder.DSC
                            ? cb.desc(root.get(request.sortField().getField()))
                            : cb.asc(root.get(request.sortField().getField()))
            );
        } else {
            query.orderBy(cb.asc(root.get("postedAt")));
        }
        TypedQuery<JobCard> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(typedQuery.getResultList(),pageable,getJobCardsCount(request));
    }
    private List<Predicate> buildPredicates(JobFilterRequest request,
                                            Root<JobPosting> root,
                                            CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if(request.title()!=null) {
            predicates.add(cb.like(cb.lower(root.get("title")),getLikeString(request.title())));
        }

        if(request.companyIds()!=null && !request.companyIds().isEmpty()) {
            predicates.add(
                    root.get("company").get("id").in(request.companyIds())
            );
        }
        if(request.roleId()!=null) {
            predicates.add(
                    cb.equal(root.get("role").get("id"),request.roleId())
            );
        }
        if(request.country() != null ) {
            predicates.add(
                    cb.like(cb.lower(root.get("location").get("country")),getLikeString(request.country()))
            );
        }
        if(request.state() != null ) {
            predicates.add(
                    cb.like(cb.lower(root.get("location").get("state")),getLikeString(request.state()))
            );
        }
        if(request.city() != null ) {
            predicates.add(
                    cb.like(cb.lower(root.get("location").get("city")),getLikeString(request.city()))
            );
        }
        if(request.skillIds()!=null && !request.skillIds().isEmpty()) {
            Join<JobPosting, JobSkillRequirement> join = root.join("skillRequirements", JoinType.INNER);
            predicates.add(
                    join.get("skill").get("id").in(request.skillIds())
            );
        }
        if(request.workMode()!=null) {
            predicates.add(
                    cb.equal(root.get("workMode"),request.workMode())
            );
        }
        if(request.salaryGe()!=null) {
            predicates.add(
                    cb.greaterThanOrEqualTo(root.get("salaryLower"),request.salaryGe())
            );
        }
        if(request.salaryLe()!=null) {
            predicates.add(
                    cb.lessThanOrEqualTo(root.get("salaryHigher"),request.salaryLe())
            );
        }
        if(request.postedAfter()!=null) {
            predicates.add(
                    cb.greaterThanOrEqualTo(root.get("postedAt"),request.postedAfter())
            );
        }
        return predicates;
    }
    private long getJobCardsCount(JobFilterRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<JobPosting> root = query.from(JobPosting.class);
        List<Predicate> predicates = buildPredicates(request,root,cb);
        Predicate pred = cb.and(predicates.toArray(new Predicate[0]));
        query.where(pred);
        return entityManager.createQuery(query.select(cb.countDistinct(root))).getSingleResult();
    }
    private String getLikeString(String s) {
        return "%" + s.toLowerCase() + "%";
    }

}
