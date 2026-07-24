package com.sai.hirely.dto.candidate;

public record CandidateResponse (
        Long id,
        String firstName,
        String lastName,
        String profilePictureUrl,
        String email,
        String description
){
}
