package com.sai.hirely.exceptions;
import com.sai.hirely.dto.error.ErrorResponse;
import com.sai.hirely.exceptions.candidate.CandidateNotFoundException;
import com.sai.hirely.exceptions.candidate.CandidateSkillNotFoundException;
import com.sai.hirely.exceptions.candidate.RoleNotFoundException;
import com.sai.hirely.exceptions.candidate.SkillNotFoundException;
import com.sai.hirely.exceptions.company.EntityNotFoundException;
import com.sai.hirely.exceptions.file.EmptyFileException;
import com.sai.hirely.exceptions.file.InvalidFileException;
import com.sai.hirely.exceptions.file.PayloadTooLargeException;
import jakarta.validation.Payload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            EntityNotFoundException.class,
    })
    public ResponseEntity<ErrorResponse> getNotFound(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                e.getMessage()
        ));
    }
    @ExceptionHandler({
            InvalidFileException.class
    })
    public ResponseEntity<ErrorResponse> invalidFileType(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(
                new ErrorResponse(e.getMessage())
        );
    }

    @ExceptionHandler({
            EmptyFileException.class
    })
    public ResponseEntity<ErrorResponse> emptyFileException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(e.getMessage())
        );
    }

    @ExceptionHandler({
            PayloadTooLargeException.class
    })
    public ResponseEntity<ErrorResponse> payloadTooLarge(RuntimeException e)  {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(new ErrorResponse(e.getMessage()));
    }
    @ExceptionHandler(
            EmailNotFounctException.class
    )
    public ResponseEntity<ErrorResponse> emailNotFoundException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorResponse> handleInternalAuth(InternalAuthenticationServiceException ex) {
        // DaoAuthenticationProvider wraps UserDetailsService exceptions in this;
        // unwrap to get the real message (e.g. "Email not found")
        String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResponse("Invalid email or password")
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResponse("Invalid email or password")
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResponse("Authentication failed. Please check your credentials.")
        );
    }
}
