package com.example.taggo.domain.common.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@ToString
public enum ErrorType {
    NOTFOUND_USER(NOT_FOUND,"찾을 수 없는 회원 입니다.");

    private HttpStatus status;
    private String message;

    ErrorType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
