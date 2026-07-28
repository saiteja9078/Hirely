package com.sai.hirely.models.candidate;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CandidateSkillKey implements Serializable {
    private Long skillId;
    private Long candidateId;
    public CandidateSkillKey() {}
    public CandidateSkillKey(Long skillId,Long candidateId) {
        this.skillId = skillId;
        this.candidateId = candidateId;
    }

    public boolean equals(Object other) {
        if(other == this) return true;
        if(other == null || other.getClass() != getClass()) return false;
        CandidateSkillKey otherKey = (CandidateSkillKey) other;
        return this.skillId.equals(otherKey.skillId)
                && this.candidateId.equals(otherKey.candidateId);
    }
    public int hashCode() {
        return Objects.hash(skillId,candidateId);
    }
    public String toString() {
        return "Candidate Id: " + this.candidateId + "Skill Id: " + this.skillId;
    }
}

