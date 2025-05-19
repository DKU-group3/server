package com.example.taggo.domain.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionResponse<Void>> handleBaseException(BaseException ex) {
        ErrorType error = ex.getErrorType();
        ExceptionResponse<Void> response = ExceptionResponse.fail(
                error.name(),  // Enum 이름을 코드로 사용
                error.getMessage()
        );
        return ResponseEntity.status(error.getStatus()).body(response);
    }
}
