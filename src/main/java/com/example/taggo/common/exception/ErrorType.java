package com.example.taggo.common.exception;

import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorType {
    NOTFOUND_USER(NOT_FOUND,"찾을 수 없는 회원 입니다."),
    DUPLICATED_EMAIL(BAD_REQUEST, "해당 이메일로 가입된 정보가 존재합니다."),
    OUTMATCHED_PASSWORD(UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    NOTFOUND_PLACE(NOT_FOUND, "찾을 수 없는 가게 입니다."),
    INVALID_TOKEN(UNAUTHORIZED, "유효하지 않은 토큰입니다.");

    private HttpStatus status;
    private String message;

    ErrorType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
