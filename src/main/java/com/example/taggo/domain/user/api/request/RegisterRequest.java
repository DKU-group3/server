package com.example.taggo.domain.user.api.request;


import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        //To-do 이메일, 패스워드 패턴 형식 지정
        String email,

        @NotNull String password,

        @NotNull String name

) {
}
