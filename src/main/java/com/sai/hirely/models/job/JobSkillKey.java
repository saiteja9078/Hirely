package com.sai.hirely.models.job;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class JobSkillKey implements Serializable
{
    private Long jobPostingId;
    private Long skillId;
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || o.getClass() != getClass()) return false;
        JobSkillKey other = (JobSkillKey)o;
        return this.jobPostingId.equals(other.jobPostingId)
                && this.skillId.equals(other.skillId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(jobPostingId,skillId);
    }
}
