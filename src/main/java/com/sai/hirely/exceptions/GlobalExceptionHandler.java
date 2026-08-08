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
}
