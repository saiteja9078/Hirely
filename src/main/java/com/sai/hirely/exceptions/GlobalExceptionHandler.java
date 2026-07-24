package com.sai.hirely.exceptions;
import com.sai.hirely.dto.error.ErrorResponse;
import com.sai.hirely.exceptions.candidate.CandidateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<ErrorResponse> candidateNotFound(CandidateNotFoundException e) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "Not found",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
