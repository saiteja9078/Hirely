package com.sai.hirely.exceptions.candidate;

import com.sai.hirely.models.candidate.CandidateSkill;
import com.sai.hirely.models.candidate.CandidateSkillKey;

public class CandidateSkillNotFoundException extends RuntimeException{
    public CandidateSkillNotFoundException(Long id) {
        super("Candidate has no such skill: "+id);
    }
    public CandidateSkillNotFoundException(CandidateSkillKey id) {
        super("Either candidate or skill not valid" + id);
    }
}
