package com.sai.hirely.dto.auth;

import com.sai.hirely.models.enums.Gender;
import com.sai.hirely.models.utils.Location;
import java.util.List;

public record CandidateSignupRequest(
        String firstName,
        String lastName,
        Gender gender,
        Integer age,
        String description,
        String email,
        String password,
        Location location,
        List<Long> skillsList
) {
}
