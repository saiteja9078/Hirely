package com.sai.hirely.exceptions;
import com.sai.hirely.dto.error.ErrorResponse;
import com.sai.hirely.exceptions.candidate.CandidateNotFoundException;
import com.sai.hirely.exceptions.candidate.CandidateSkillNotFoundException;
import com.sai.hirely.exceptions.candidate.RoleNotFoundException;
import com.sai.hirely.exceptions.candidate.SkillNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            CandidateNotFoundException.class,
            SkillNotFoundException.class,
            CandidateSkillNotFoundException.class,
            RoleNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> getNotFound(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                e.getMessage()
        ));
    }
}
