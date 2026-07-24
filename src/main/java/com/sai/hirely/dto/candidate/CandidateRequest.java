package com.sai.hirely.dto.candidate;
import com.sai.hirely.models.enums.Gender;
import java.util.List;

public record CandidateRequest(
        String firstName,
        String lastName,
        Gender gender,
        Integer age,
        String description,
        String email,
        String password,
        List<Long> skillsList
) {
}
