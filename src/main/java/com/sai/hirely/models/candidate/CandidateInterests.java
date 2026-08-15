package com.sai.hirely.models.candidate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "candidate_interests")
public class CandidateInterests {

    @EmbeddedId
    private CandidateInterestsKey id;

    public CandidateInterests() {
        id = new CandidateInterestsKey();
    }

    // more preferences
}
