package com.sai.hirely.dto.auth;

public record AuthenticationResponse(
        String token,
        String email
) {
}
