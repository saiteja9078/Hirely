package com.sai.hirely.models.candidate;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CandidateExperienceKey implements Serializable
{
    private Long candidateId;
    private Long roleId;
    private Long companyId;

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || other.getClass() != getClass()) return false;
        CandidateExperienceKey otherKey = (CandidateExperienceKey) other;
        return this.candidateId.equals(otherKey.candidateId)
                && this.roleId.equals(otherKey.roleId)
                && this.companyId.equals(otherKey.companyId);
    }
    public int hashCode() {
        return Objects.hash(this.candidateId,this.roleId,this.companyId);
    }
}
