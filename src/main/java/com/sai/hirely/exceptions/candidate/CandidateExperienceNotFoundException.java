package com.sai.hirely.exceptions.candidate;

public class CandidateExperienceNotFoundException extends RuntimeException {
    public CandidateExperienceNotFoundException(Long id) {
        super("Experience with id: "+id+" not found");
    }
}
