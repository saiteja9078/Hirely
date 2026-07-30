package com.sai.hirely.repository.specifications.jobPosting;

import com.sai.hirely.models.job.JobPosting;
import com.sai.hirely.models.job.JobSkillRequirement;
import com.sai.hirely.models.utils.Location;
import com.sai.hirely.models.utils.WorkMode;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public class JobSpecification {

    public static Specification<JobPosting> title(String title) {
        return (ro, qu, cb) -> {
            if( title==null || title.isBlank()) {
                return null;
            }
            return cb.like(cb.lower(ro.get("title")) , "%"+title.toLowerCase()+"%");
        };
    }
    public static Specification<JobPosting> workMode(WorkMode mode) {
        return (ro,qu,cb) -> {
            if(mode==null) return null;
            return cb.equal(ro.get("workMode"),mode);
        };
    }
    public static Specification<JobPosting> ge(Integer ge) {
        return (ro,qu,cb)-> {
            if(ge == null)return null;
            return cb.ge(ro.get("salaryLower"),ge);
        };
    }
    public static Specification<JobPosting> le(Integer le) {
        return (ro,qu,cb)-> {
            if(le == null)return null;
            return cb.le(ro.get("salaryGreater"),le);
        };
    }
    public static Specification<JobPosting> location(Location location) {
        return null;
    }
    public static Specification<JobPosting> companyIn(List<Long> companyIds) {
        return (ro, qu, cb) -> {
            if (companyIds == null || companyIds.isEmpty()) return null;
                return ro.get("company").get("id").in(companyIds);
        };
    }
    public static Specification<JobPosting> role(List<Long> roleIds) {
        return (ro, qu, cb) -> {
            if (roleIds == null) return null;
            return ro.get("role").get("id").in(roleIds);
        };
    }
    public static Specification<JobPosting> role(Long roleId) {
        return (ro, qu, cb) -> {
            if (roleId == null) return null;
            return cb.equal(ro.get("role").get("id"),roleId);
        };
    }

    public static Specification<JobPosting> skillIn(List<Long> skillIds) {
        return (ro, qu, cb) -> {
            if (skillIds == null) return null;
            qu.distinct(true);
            Join<JobPosting, JobSkillRequirement> join = ro.join("jobSkills");
            return join.get("skill").get("id").in(skillIds);
        };
    }

    public static Specification<JobPosting> country(String country) {
        return (root, query, cb) -> {
            if (country == null || country.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("location").get("country")),
                    "%" + country.toLowerCase() + "%"
            );
        };
    }

    public static Specification<JobPosting> state(String state) {
        return (root, query, cb) -> {
            if (state == null || state.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("location").get("state")),
                    "%" + state.toLowerCase() + "%"
            );
        };
    }

    public static Specification<JobPosting> city(String city) {
        return (root, query, cb) -> {
            if (city == null || city.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("location").get("city")),
                    "%" + city.toLowerCase() + "%"
            );
        };
    }
    public static Specification<JobPosting> postedAfter(LocalDateTime dateTime) {
        return (root, query, cb) -> {
            if(dateTime==null)return null;
            return cb.greaterThanOrEqualTo(root.get("postedAt"),dateTime);
        };
    }
}
