package com.sai.hirely.service.candidate;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
public class CandidateInterestsKey {
    private long candidateId;
    private long roleId;

    public CandidateInterestsKey(long candidateId,long roleId) {
        this.candidateId = candidateId;
        this.roleId = roleId;
    }
    public CandidateInterestsKey() {
    }
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o != null && o.getClass() != getClass()) return false;

        CandidateInterestsKey other = (CandidateInterestsKey) o;
        return other.candidateId == this.candidateId &&
                other.roleId == this.roleId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(candidateId,roleId);
    }
}
