package com.sai.hirely.dto.candidate;

import com.sai.hirely.models.utils.Location;

public record CandidateResponse (
        Long id,
        String firstName,
        String lastName,
        String profilePictureUrl,
        String email,
        String description,
        Location location
){
}
