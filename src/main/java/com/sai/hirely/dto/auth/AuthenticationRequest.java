package com.sai.hirely.dto.auth;


import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;

public record AuthenticationRequest(
       @Email @Nonnull String username,
        @Nonnull String password
) {
}
