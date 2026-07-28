package com.sai.hirely.exceptions.candidate;

public class CandidateNotFoundException extends RuntimeException{
    public CandidateNotFoundException(Long id) {
        super("Candidate with idL "+id+" not exists");
    }
}
