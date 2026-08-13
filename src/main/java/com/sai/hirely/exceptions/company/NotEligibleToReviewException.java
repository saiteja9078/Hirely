package com.sai.hirely.exceptions.company;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotEligibleToReviewException extends RuntimeException {
    public NotEligibleToReviewException(String message) {
        super(message);
    }
}
