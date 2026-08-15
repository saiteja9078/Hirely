package com.sai.hirely.security;

import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class CurrentUser {
    private CurrentUser() {
    }

    public static CustomUserDetails require(CustomUserDetails user, AccountType... allowedTypes) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sign in to continue.");
        }
        for (AccountType allowedType : allowedTypes) {
            if (user.getType() == allowedType) {
                return user;
            }
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This account cannot perform that action.");
    }

    public static void requireId(CustomUserDetails user, Long id) {
        if (user == null || !user.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only manage your own account.");
        }
    }
}
