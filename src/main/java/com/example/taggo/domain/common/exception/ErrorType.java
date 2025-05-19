package com.example.taggo.domain.common.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@ToString
public enum ErrorType {
    NOTFOUND_USER(NOT_FOUND,"찾을 수 없는 회원 입니다."),
    DUPLICATED_EMAIL(BAD_REQUEST, "해당 이메일로 가입된 정보가 존재합니다."),
    OUTMATCHED_PASSWORD(UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    NOTFOUND_PLACE(NOT_FOUND, "찾을 수 없는 가게 입니다.");

    private HttpStatus status;
    private String message;

    ErrorType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
