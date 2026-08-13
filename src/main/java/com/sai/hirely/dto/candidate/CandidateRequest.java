package com.sai.hirely.dto.candidate;
import com.sai.hirely.models.enums.Gender;
import com.sai.hirely.models.utils.Location;
import java.util.List;

public record CandidateRequest(
        String firstName,
        String lastName,
        Gender gender,
        Integer age,
        String description,
        String email,
        Location location,
        List<Long> skillsList
) {
}
