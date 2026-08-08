package com.sai.hirely.dto.candidate;

public record CandidateCard (
    long candidateId,
    String firstName,
    String lastName,
    String profilePictureUr,
    String currentCompany,
    String currentRole
    ){
}
