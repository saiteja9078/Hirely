package com.sai.hirely.Specification;

import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.models.enums.Gender;
import org.springframework.data.jpa.domain.Specification;

public class CandidateSpecification {
    public static Specification<Candidate> hasName(String name) {
        return (root,query,cb) ->
                cb.equal(root.get("name"), name);
    }
    public static Specification<Candidate> ageRange(Long left,Long right) {
        return (root,query,cb) ->
                cb.and(cb.ge(root.get("age"),left),cb.le(root.get("age"),right));
    }
    public static Specification<Candidate> gender(String gender) {
        return (root,query,cb) ->
                cb.equal(root.get("gender"), Gender.valueOf(gender));
    }
}
